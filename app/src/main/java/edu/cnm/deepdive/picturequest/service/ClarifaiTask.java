package edu.cnm.deepdive.picturequest.service;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import com.google.gson.InstanceCreator;
import com.google.gson.annotations.SerializedName;
import edu.cnm.deepdive.picturequest.CameraFragment;
import edu.cnm.deepdive.picturequest.MainActivity;
import edu.cnm.deepdive.picturequest.R;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Input;
import java.io.File;
import java.util.List;

/**This class implements and utilizes the Clarifai client by creating a new client using the apiKey then
making a call to the GeneralModel to receive an output list of tagged images upon a file captured
from the camerafragment class. It does this in the background then uses the output names to correspond
with a possible choice for the scene we are currently at.*/

public class ClarifaiTask extends AsyncTask<File, Void, List<ClarifaiOutput<Concept>>> {

  private Context context;
  private long playerId;
  private long sceneId;

  public ClarifaiTask(Context context, long playerId, long sceneId) {
    this.context = context;
    this.sceneId = sceneId;
    this.playerId = playerId;
  }

  @Override
  protected List<ClarifaiOutput<Concept>> doInBackground(File... files) {
    ClarifaiClient client = new ClarifaiBuilder(context.getString(R.string.api_key)).buildSync();
    ClarifaiResponse<List<ClarifaiOutput<Concept>>> response =
        client.getDefaultModels().generalModel().predict()
            .withInputs(ClarifaiInput.forImage(files[0])).executeSync();
    if (response.isSuccessful()) {
      PictureQuestDatabase db = PictureQuestDatabase.getInstance(context);

      final List<ClarifaiOutput<Concept>> predictions = response.get();
      for (ClarifaiOutput<Concept> result : predictions) {
        for(Concept datum : result.data()) {
          String name = datum.name();
          Input newInput = new Input();
          newInput.setName(name);
          newInput.setSceneId(sceneId);                    //    TODO how to pass the exact scene we are on? More SQL?
          newInput.setPlayerId(playerId);                  //    TODO how to set it as the current player from here outside of a fragment?
          db.getInputDao().insert(newInput);
        }
      }
      return response.get();
    } else {
      cancel(true);
    }
    return null;
  }

  @Override
  protected void onPostExecute(List<ClarifaiOutput<Concept>> response) {
    //TODO this is for UI updates upon success

  }

  @Override
  protected void onCancelled(List<ClarifaiOutput<Concept>> clarifaiOutputs) {
    Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_LONG).show();
  }

}
