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
                  new PopulateDbTask(INSTANCE, context).execute();
                }
              }).build();
        }
      }
    }
    return INSTANCE;
  }

  private static class PopulateDbTask extends AsyncTask<Void, Void, Void> {

    private final PictureQuestDatabase db;

    private final Context context;

    PopulateDbTask(PictureQuestDatabase db, Context context){
      this.db = db;
      this.context = context;
    }


    //TODO write a bunch more code to pre-populate the database with all Scenes, possible choices, and choice synonyms.
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

// TODO create a method that will extract from the res/raw json files.



}
