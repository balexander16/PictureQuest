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

package edu.cnm.deepdive.picturequest.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import edu.cnm.deepdive.picturequest.model.entity.Scene;

/**
 * {@link androidx.lifecycle.ViewModel} class for the {@link edu.cnm.deepdive.picturequest.SceneFragment} to
 * access {@link LiveData} off of the UI thread from {@link androidx.room.RoomDatabase}
 *
 */
public class SceneViewModel extends AndroidViewModel {

  /**
   * {@link LiveData} {@link Scene} variable to keep track of the current {@link Scene} to be passed to the {@link edu.cnm.deepdive.picturequest.SceneFragment}
   */
  private LiveData<Scene> scene;
  /**
   * {@link MutableLiveData} {@link Long} a filter for our live data to be used in a {@link Transformations}{@link java.util.Map}
   * to keep track of when the {@link Player} is updated so anytime it changes our {@link androidx.lifecycle.Observer} knows.
   */
  private MutableLiveData<Long> filterLiveData = new MutableLiveData<>();

  /**
   * Constructor for the ViewModel association to the {@link edu.cnm.deepdive.picturequest.SceneFragment}
   * This constructor is using a {@link Transformations}  for the {@link LiveData} {@link #scene} field above
   * to check for anytime the player is updated into the database. This takes in the {@link #filterLiveData}
   * as the key checking if there are any changes. Initializes the instance.
   * @param application the application
   */
  public SceneViewModel(@NonNull Application application) {
    super(application);
    scene = Transformations.switchMap(filterLiveData, (v) ->
        PictureQuestDatabase.getInstance(getApplication()).getSceneDao().findById(v));
  }

  /**
   * Method to set the {@link #scene} by an id
   * @param id the id to set the {@link #scene} to
   */
  public void setSceneId(Long id) {
    filterLiveData.setValue(id);
  }

  /**
   * Method to get the {@link #scene}
   * @return the {@link #scene} you got
   */
  public LiveData<Scene> getScene() {
    return scene;
  }

}