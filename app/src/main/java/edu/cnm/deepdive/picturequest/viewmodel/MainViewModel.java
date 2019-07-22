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

  public MainViewModel(@NonNull Application application) {
    super(application);
    player = Transformations.switchMap(filterLiveData, (v) ->
        PictureQuestDatabase.getInstance(getApplication()).getPlayerDao().findById(v));
  }

  public void setPlayerId(long id) {
    filterLiveData.setValue(id);
  }

  public LiveData<Player> getPlayer() {
    return player;
  }

  public void updatePlayer(Player player) {
    new Thread(() -> PictureQuestDatabase.getInstance(getApplication()).getPlayerDao().update(player)).start();
  }

  public void clearInputs(long sceneId, long playerId) {
    new Thread( () -> {
      PictureQuestDatabase.getInstance(getApplication()).getInputDao().deleteInputs(sceneId, playerId);
    }).start();
  }

}
