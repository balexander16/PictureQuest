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

package edu.cnm.deepdive.picturequest.model.database;


import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.picturequest.R;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Class to create a {@link RoomDatabase} containing methods to get the {@link androidx.room.Dao} classes
 * {@link SceneDao}, {@link InputDao}, {@link ChoiceDao}, {@link ChoiceSynonymDao}, and {@link PlayerDao}.
 * Also contains methods to create the {@link RoomDatabase} itself if it has not yet been created. This class
 * contains a nested class: {@link PopulateDbTask} an {@link AsyncTask} to populate the database with the
 * {@link Scene}s, {@link Choice}s, and {@link ChoiceSynonym} for the game.
 * @author Brian Alexander
 */
@Database(entities = {Player.class, Scene.class, Input.class, Choice.class, ChoiceSynonym.class}, version = 1)
public abstract class PictureQuestDatabase  extends RoomDatabase {

  /**
   * Method to get the {@link InputDao}
   * @return {@link InputDao}
   */
  public abstract InputDao getInputDao();

  /**
   * Method to get the {@link SceneDao}
   * @return {@link SceneDao}
   */
  public abstract SceneDao getSceneDao();

  /**
   * Method to get the {@link ChoiceDao}
   * @return {@link ChoiceDao}
   */
  public abstract ChoiceDao getChoiceDao();

  /**
   * Method to get the {@link ChoiceSynonymDao}
   * @return {@link ChoiceSynonymDao}
   */
  public abstract ChoiceSynonymDao getChoiceSynonymDao();

  /**
   * Method to get the {@link PlayerDao}
   * @return
   */
  public abstract PlayerDao getPlayerDao();

  /**
   * Initializing the Database and its instance.
   */
  private static PictureQuestDatabase INSTANCE;

  /**
   * Method to get the instance of the app itself and if null, create a the database itself.
   * @param context Current {@link Context} of the app passed into the {{@link #getInstance(Context)}}
   * @return The {@link RoomDatabase} created for the game.
   */
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
                  new PopulateDbTask(INSTANCE, context).execute();
                }
              }).build();
        }
      }
    }
    return INSTANCE;
  }

  /**
   * Class created to prepopulate the {@link RoomDatabase} done using an {@link AsyncTask} to keep database
   * population out of the UI thread.
   */
  private static class PopulateDbTask extends AsyncTask<Void, Void, Void> {

    /**
     * Initialization of the {@link RoomDatabase}
     */
    private final PictureQuestDatabase db;

    /**
     * Context of the app.
     */
    private final Context context;

    /**
     * Constructor for this class.
     * @param db the current {@link RoomDatabase}
     * @param context the current app {@link Context}
     */
    PopulateDbTask(PictureQuestDatabase db, Context context){
      this.db = db;
      this.context = context;
    }


    /**
     * {@link AsyncTask} do in background, to initialize a background thread that populated the
     * {@link ChoiceSynonym}, {@link Choice} and {@link Scene} Entity of the {@link RoomDatabase}
     * by creating a {@link GsonBuilder}, reading a local {@link com.google.gson.JsonArray} Json file with {@link InputStream}
     * then reading the Stream with a {@link Reader}. From there we take an {@link java.util.Arrays} of
     * the class type we read in from the file. We then use our local initialization of our {@link RoomDatabase}
     * call the {@link androidx.room.Dao} class associated to the entity, {@link SceneDao}, {@link ChoiceDao}, or
     * {@link ChoiceSynonymDao} and their respective multiple insert methods.
     * @param voids nothing...
     * @return nothing...
     */
    @Override
    protected Void doInBackground(Void... voids) {
      /*
      These lines are used to populate the choice entity of the data base, the ordering is important because of
       the foreign key constraints. Must be scene, then choice, then choicesynonyms.
       */
      Gson gson = new GsonBuilder().create();

      InputStream inputScene = context.getResources().openRawResource(R.raw.jsonscenes);
      Reader readerScenes = new InputStreamReader(inputScene);
      Scene[] scenes = gson.fromJson(readerScenes, Scene[].class);
      db.getSceneDao().insert(scenes);

      InputStream input = context.getResources().openRawResource(R.raw.jsonchoices);
      Reader reader = new InputStreamReader(input);
      Choice[] choices = gson.fromJson(reader, Choice[].class);       //reads an array of objects(Choice) and gson seperates what I need, fucking brilliant
      db.getChoiceDao().insert(choices);

      InputStream inputSynonyms = context.getResources().openRawResource(R.raw.jsonchoicesynonyms);
      Reader readerSynonyms = new InputStreamReader(inputSynonyms);
      ChoiceSynonym[] synonyms = gson.fromJson(readerSynonyms, ChoiceSynonym[].class);
      db.getChoiceSynonymDao().insert(synonyms);

      return null;
    }

  }

}
