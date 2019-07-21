/*
    Copyright 2019 Brian Alexander

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

*/

package edu.cnm.deepdive.picturequest.service;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import edu.cnm.deepdive.picturequest.BuildConfig;
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
    ClarifaiClient client = new ClarifaiBuilder(BuildConfig.API_KEY).buildSync();
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
          newInput.setSceneId(sceneId);
          newInput.setPlayerId(playerId);
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
    // Nothing really
  }

  @Override
  protected void onCancelled(List<ClarifaiOutput<Concept>> clarifaiOutputs) {
    Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_LONG).show();
  }

}
