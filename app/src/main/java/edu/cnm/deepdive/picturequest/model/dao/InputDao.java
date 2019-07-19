package edu.cnm.deepdive.picturequest.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.picturequest.model.entity.Input;
import java.util.List;

@Dao
public interface InputDao {

  @Insert
  void insert(Input input);

  @Query("SELECT * FROM  input")
  LiveData<List<Input>> getAll();

  @Query("SELECT name FROM input WHERE scene_id = :sceneId")
  LiveData<List<String>> getAllByScene(long sceneId);                               //FIXME! Observe you first then put you in the next one


  @Query("DELETE FROM Input WHERE scene_id = :sceneId AND player_id = :playerId")
  void deleteInputs(long sceneId, long playerId);


}
