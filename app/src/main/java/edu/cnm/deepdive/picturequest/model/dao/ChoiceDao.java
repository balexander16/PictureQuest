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
