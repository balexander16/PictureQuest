package edu.cnm.deepdive.picturequest.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Player;

public class MainViewModel extends AndroidViewModel {

  private LiveData<Player> player;
  private MutableLiveData<Long> filterLiveData = new MutableLiveData<>();

  public MainViewModel(@NonNull Application application) {
    super(application);
    player = Transformations.switchMap(filterLiveData, (v) ->
        PictureQuestDatabase.getInstance(getApplication()).getPlayerDao().findById(v));
  }

  public void setPlayerId(Long id) {
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
