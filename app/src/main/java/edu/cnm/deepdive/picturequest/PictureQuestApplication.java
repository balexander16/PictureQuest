package edu.cnm.deepdive.picturequest;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Player;


public class PictureQuestApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    new Thread(() -> {
      Player player = new Player();
      player.setPlayer("Brian");
      PictureQuestDatabase.getInstance(this).getPlayerDao().insert(player);
    }).start();
  }
}
