package edu.cnm.deepdive.picturequest.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import java.util.List;

@Dao
public interface PlayerDao {

  @Insert
  void insert(Player player);

  @Query("SELECT * FROM player")
  LiveData<List<Player>> getAll();

}
