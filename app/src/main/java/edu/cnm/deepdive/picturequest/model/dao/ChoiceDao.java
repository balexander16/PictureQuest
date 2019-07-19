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

  @Insert
  void insert(Choice... choices);

  @Query("SELECT * FROM choice")
  LiveData<List<Choice>> getAll();

  @Query("SELECT * FROM choice WHERE id = :id")
  LiveData<Choice> findById(long id);

  @Query(""
      + "SELECT DISTINCT "
      + "c.* "
      + "FROM Choice c "
      + "LEFT JOIN ChoiceSynonym s"
      + " ON s.choice_id = c.id "
      + "WHERE "
      + "c.from_scene_id = :sceneId "
      + "AND "
      + "(c.name in (:objects) OR s.name in (:objects));"
  )
  LiveData<List<Choice>> getRelevantChoices(long sceneId, List<String> objects);          //FIXME if not correct

}
