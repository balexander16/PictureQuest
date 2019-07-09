package edu.cnm.deepdive.picturequest.model.database;


import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.picturequest.model.dao.ChoiceDao;
import edu.cnm.deepdive.picturequest.model.dao.ChoiceSynonymDao;
import edu.cnm.deepdive.picturequest.model.dao.InputDao;
import edu.cnm.deepdive.picturequest.model.dao.PlayerDao;
import edu.cnm.deepdive.picturequest.model.dao.SceneDao;
import edu.cnm.deepdive.picturequest.model.entity.Choice;
import edu.cnm.deepdive.picturequest.model.entity.ChoiceSynonym;
import edu.cnm.deepdive.picturequest.model.entity.Input;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import edu.cnm.deepdive.picturequest.model.entity.Scene;

@Database(entities = {Player.class, Scene.class, Input.class, Choice.class, ChoiceSynonym.class}, version = 1)
public abstract class PictureQuestDatabase  extends RoomDatabase {

  public abstract InputDao getInputDao();

  public abstract SceneDao getSceneDao();

  public abstract ChoiceDao getChoiceDao();

  public abstract ChoiceSynonymDao getChoiceSynonymDao();

  public abstract PlayerDao getPlayerDao();

  private static PictureQuestDatabase INSTANCE;

  public static PictureQuestDatabase getInstance(Context context){
    if(INSTANCE == null){
      synchronized (PictureQuestDatabase.class) {
        if(INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              PictureQuestDatabase.class,
              "pq_room")
              .addCallback(new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                  super.onCreate(db);
                  new PopulateDbTask(INSTANCE).execute();
                }
              }).build();
        }
      }
    }
    return INSTANCE;
  }

  private static class PopulateDbTask extends AsyncTask<Void, Void, Void> {

    private final PictureQuestDatabase db;

    PopulateDbTask(PictureQuestDatabase db){
      this.db = db;
    }


    //TODO write a bunch more code to pre-populate the database with all Scenes, possible choices, and choice synonyms.
    @Override
    protected Void doInBackground(Void... voids) {
      Scene scene1 = new Scene();
      scene1.setScene("Stepping out of the darkness of your home you find yourself in the town square.");
      db.getSceneDao().insert(scene1);
      return null;
    }

    // TODO take file from res.
  }

// TODO create a method that will extract from the res/raw json files.



}
