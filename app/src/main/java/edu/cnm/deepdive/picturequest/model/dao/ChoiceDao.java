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

/**
 * {@link Dao} Interface used to interact with {@link Choice}, contains
 * methods for Inserting, Finding by Id, as well as getting Relevant Choices to
 * the specific scene and what inputs are given.
 *
 * @author Brian Alexander
 */
@Dao
public interface ChoiceDao {

  /**
   * Simple method to insert a single choice into the database.
   * @param choice the {@link Choice} to be inserted.
   */
  @Insert
  void insert(Choice choice);

  /**
   * Method to insert zero or more choices to the database at once.
   * @param choices the {@link Choice} to be inserted
   */
  @Insert
  void insert(Choice... choices);

  /**
   * Currently unused method that gets all choices from the Database
   * @return {@link LiveData} {@link List} of {@link Choice} in the entire table.
   */
  @Query("SELECT * FROM choice")
  LiveData<List<Choice>> getAll();

  /**
   * Method to select a choice by its id.
   * @param id the id number of the choice, as a long.
   * @return the {@link Choice} by its id.
   */
  @Query("SELECT * FROM choice WHERE id = :id")
  LiveData<Choice> findById(long id);

  /**
   * Method that selects all {@link Choice} or {@link edu.cnm.deepdive.picturequest.model.entity.ChoiceSynonym} that match
   * with an {@link edu.cnm.deepdive.picturequest.model.entity.Input} generated from a picture being taken and passed into {@link edu.cnm.deepdive.picturequest.service.ClarifaiTask}
   * @param sceneId the sceneId that
   * @param objects {@link List} of {@link String} objects that are passed in from the {@link edu.cnm.deepdive.picturequest.service.ClarifaiTask}
   * @return list of {@link Choice} that mathches the {@link edu.cnm.deepdive.picturequest.model.entity.Input} names.
   */
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
