package edu.cnm.deepdive.picturequest.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Scene;
import java.util.List;

public class SceneViewModel extends AndroidViewModel {

  private LiveData<List<Scene>> scene;

  public SceneViewModel(@NonNull Application application) {
    super(application);
  }

  public LiveData<List<Scene>> getSceneLiveData() {
    if (scene == null) {
      scene = PictureQuestDatabase.getInstance(getApplication()).getSceneDao().getAll();
    }
    return scene;
  }

}