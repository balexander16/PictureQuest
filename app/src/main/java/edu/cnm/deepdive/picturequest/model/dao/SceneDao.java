package edu.cnm.deepdive.picturequest.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.picturequest.model.entity.Scene;
import java.util.List;

@Dao
public interface SceneDao {

  @Insert
  void insert(Scene scene);

  @Query("SELECT * FROM scene")
  LiveData<List<Scene>> getAll();

  @Query("SELECT * FROM scene WHERE id = :id")
  LiveData<Scene>findById(long id);
}