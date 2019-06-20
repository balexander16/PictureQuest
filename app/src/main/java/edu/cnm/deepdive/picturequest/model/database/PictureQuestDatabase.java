package edu.cnm.deepdive.picturequest.model.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Choice;
import edu.cnm.deepdive.picturequest.model.entity.ChoiceSynonym;
import edu.cnm.deepdive.picturequest.model.entity.Input;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import edu.cnm.deepdive.picturequest.model.entity.Scene;

@Database(entities = {Player.class, Scene.class, Input.class, Choice.class, ChoiceSynonym.class}, version = 1)
public abstract class PictureQuestDatabase  extends RoomDatabase {


  private static PictureQuestDatabase INSTANCE;

  public static PictureQuestDatabase getInstance(Context context){
    if(INSTANCE == null){
      INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PictureQuestDatabase.class,
          "picture_quest_room").build();
    }
    return INSTANCE;
  }

}
