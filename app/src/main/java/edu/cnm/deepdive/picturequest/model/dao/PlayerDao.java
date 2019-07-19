package edu.cnm.deepdive.picturequest.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import java.util.List;

@Dao
public interface PlayerDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Player player);

  @Query("SELECT * FROM player WHERE authentication_id = :authenticationId")
  Player get(String authenticationId);

  @Query("SELECT * FROM player")
  LiveData<List<Player>> getAll();

  @Query("SELECT * FROM player WHERE id = :id")
  LiveData<Player> findById(long id);

  @Update
  int update(Player... players);
}
