package edu.cnm.deepdive.picturequest.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.picturequest.model.entity.Choice;
import java.util.List;

@Dao
public interface ChoiceDao {

  @Insert
  void insert(Choice choice);

  @Query("SELECT * FROM choice")
  LiveData<List<Choice>>getAll();

}
