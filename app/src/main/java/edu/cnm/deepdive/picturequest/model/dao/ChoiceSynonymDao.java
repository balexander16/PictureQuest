package edu.cnm.deepdive.picturequest.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.picturequest.model.entity.ChoiceSynonym;
import java.util.List;

@Dao
public interface ChoiceSynonymDao {

  @Insert
  void insert(ChoiceSynonym choiceSynonym);

  @Insert
  void insert(ChoiceSynonym... choicesSynonyms);

  @Query("SELECT * FROM choicesynonym")
  LiveData<List<ChoiceSynonym>> getAll();

}
