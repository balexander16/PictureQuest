package edu.cnm.deepdive.picturequest.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Scene;

public class SceneViewModel extends AndroidViewModel {

  private LiveData<Scene> scene;
  private MutableLiveData<Long> filterLiveData = new MutableLiveData<>();


  public SceneViewModel(@NonNull Application application) {
    super(application);
    scene = Transformations.switchMap(filterLiveData, (v) ->
        PictureQuestDatabase.getInstance(getApplication()).getSceneDao().findById(v));
  }

  public void setSceneId(Long id) {
    filterLiveData.setValue(id);
  }

  public LiveData<Scene> getScene() {
    return scene;
  }

}