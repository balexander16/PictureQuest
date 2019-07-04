package edu.cnm.deepdive.picturequest.service;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
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
import edu.cnm.deepdive.picturequest.MainActivity;
import edu.cnm.deepdive.picturequest.R;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Input;
import java.io.File;
import java.util.List;

public class ClarifaiTask extends AsyncTask<File, Void, List<ClarifaiOutput<Concept>>> {

  // d4db789e7e4d4b10ad75d7113be86fee   TODO resource API key

  private Context context;

  public ClarifaiTask(Context context) {
    this.context = context;
  }

  @Override
  protected List<ClarifaiOutput<Concept>> doInBackground(File... files) {
    ClarifaiClient client = new ClarifaiBuilder("d4db789e7e4d4b10ad75d7113be86fee").buildSync();
    ClarifaiResponse<List<ClarifaiOutput<Concept>>> response =
        client.getDefaultModels().generalModel().predict()
            .withInputs(ClarifaiInput.forImage(files[0])).executeSync();
    if (response.isSuccessful()) {
      //TODO save to the database in the background
      PictureQuestDatabase db = PictureQuestDatabase.getInstance(context);

      final List<ClarifaiOutput<Concept>> predictions = response.get();
      for (ClarifaiOutput<Concept> result : predictions) {
        for(Concept datum : result.data()) {
          String name = datum.name();
          Input newInput = new Input();
          newInput.setName(name);
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
