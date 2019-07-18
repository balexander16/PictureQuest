package edu.cnm.deepdive.picturequest;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import edu.cnm.deepdive.picturequest.model.entity.Scene;
import edu.cnm.deepdive.picturequest.service.GoogleSignInService;


public class PictureQuestApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    new Thread(() -> {
      PictureQuestDatabase.getInstance(this).getSceneDao().delete();
    }).start();
    GoogleSignInService.setContext(this);
  }


}
