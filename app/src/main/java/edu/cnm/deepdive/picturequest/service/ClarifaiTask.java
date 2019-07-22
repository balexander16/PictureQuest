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

/**
 * Class to make a call to the Clarifai API to create predictions off of an image, predicting what objects are contained within.
 * This is done as an {@link AsyncTask} to avoid running on the UI thread while we wait for a request. This {@link AsyncTask} takes in a {@link File}
 * to be converted into a {@link List} of {@link ClarifaiOutput} {@link Concept}. This is done by creating and initializing a
 * {@link ClarifaiClient} using an API_Key then making a {@link ClarifaiResponse} request with the Image which is the {@link File} passed in.
 * I have only used the General Model provided by Clarifai. I then take the {@link ClarifaiResponse} and only after a successful request. Get the specific date needed.
 * @author Brian Alexander
 */
public class ClarifaiTask extends AsyncTask<File, Void, List<ClarifaiOutput<Concept>>> {

  /**
   * Context for the application
   */
  private Context context;
  /**
   * the Id of the {@link edu.cnm.deepdive.picturequest.model.entity.Player}
   */
  private long playerId;
  /**
   * the id of the {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
   */
  private long sceneId;

  /**
   * Constructor for the class.
   * @param context current context of app passed in.
   * @param playerId the current player
   * @param sceneId the current scene.
   */
  public ClarifaiTask(Context context, long playerId, long sceneId) {
    this.context = context;
    this.sceneId = sceneId;
    this.playerId = playerId;
  }

  /**
   * Override of the doInBackground. This is to make the API request off of the UI thread.
   * Make a new {@link ClarifaiClient} with the API_Key. Then make a {@link ClarifaiResponse} request. On the file.
   * We then only on a successful response, take the {@link List} of {@link ClarifaiOutput} {@link Concept} and run it through
   * nested for loops, that say for each result in our predications, then give me the data of each result. WE then get just the name of the
   * {@link ClarifaiResponse} and create a new {@link Input} setting the name to the name from the {@link ClarifaiResponse}
   * we also set the {@link #sceneId} and the {@link #playerId} to what was taken in. Then we insert all of that using {@link edu.cnm.deepdive.picturequest.model.dao.InputDao}
   * @param files
   * @return
   */
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

  /**
   * Creates a toast if the request to Clarifai was unsuccesful
   * @param clarifaiOutputs the attempted request to the API
   */
  @Override
  protected void onCancelled(List<ClarifaiOutput<Concept>> clarifaiOutputs) {
    Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_LONG).show();
  }

}
