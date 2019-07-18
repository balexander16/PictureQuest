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


}
