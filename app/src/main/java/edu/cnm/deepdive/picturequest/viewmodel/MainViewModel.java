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

/**
 * {@link AndroidViewModel} class to keep database queries off needed for the main activity off of the UI Thread.
 */
public class MainViewModel extends AndroidViewModel {

  /**
   * {@link LiveData} {@link Player} variable to keep track of the player currently signed in then passed to fragments.
   */
  private LiveData<Player> player;
  /**
   * {@link MutableLiveData} {@link Long} a filter for our live data to be used in a {@link Transformations}{@link java.util.Map}
   * to keep track of when the {@link Player} is updated so anytime it changes our {@link androidx.lifecycle.Observer} knows.
   */
  private MutableLiveData<Long> filterLiveData = new MutableLiveData<>();

  /**
   * Constructor for the ViewModel association to the {@link edu.cnm.deepdive.picturequest.MainActivity}
   * This constructor is using a {@link Transformations}  for the {@link LiveData} {@link #player} field above
   * to check for anytime the player is updated into the database. This takes in the {@link #filterLiveData}
   * as the key checking if there are any changes.
   * @param application the application
   */
  public MainViewModel(@NonNull Application application) {
    super(application);
    player = Transformations.switchMap(filterLiveData, (v) ->
        PictureQuestDatabase.getInstance(getApplication()).getPlayerDao().findById(v));
  }

  /**
   * Setter to set the Id of the player.
   * @param id the id to set the {@link Player} to.
   */
  public void setPlayerId(long id) {
    filterLiveData.setValue(id);
  }

  /**
   * Getter to get the {@link Player} from the data base
   * @return {@link LiveData} {@link Player}
   */
  public LiveData<Player> getPlayer() {
    return player;
  }

  /**
   * Method to update the {@link Player} in the database upon scene change
   * @param player {@link Player} to be updated to
   */
  public void updatePlayer(Player player) {
    new Thread(() -> PictureQuestDatabase.getInstance(getApplication()).getPlayerDao().update(player)).start();
  }

  /**
   * Method to clear all {@link edu.cnm.deepdive.picturequest.model.entity.Input} for a specific
   * {@link edu.cnm.deepdive.picturequest.model.entity.Scene} for a {@link Player} so that upon returning to
   * a scene the {@link edu.cnm.deepdive.picturequest.model.entity.Input} will be cleared so the {@link edu.cnm.deepdive.picturequest.model.entity.Choice}
   * will not be there already.
   * @param sceneId the id of the {@link edu.cnm.deepdive.picturequest.model.entity.Scene} to have its {@link edu.cnm.deepdive.picturequest.model.entity.Input} cleared.
   * @param playerId the id of the {@link Player} to have its {@link edu.cnm.deepdive.picturequest.model.entity.Input} cleared.
   */
  public void clearInputs(long sceneId, long playerId) {
    new Thread( () -> {
      PictureQuestDatabase.getInstance(getApplication()).getInputDao().deleteInputs(sceneId, playerId);
    }).start();
  }

}
