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
import edu.cnm.deepdive.picturequest.model.entity.ChoiceSynonym;
import java.util.List;

/**
 * {@link Dao} Interface designed to interact with {@link ChoiceSynonym} Entity class, including methods to insert
 * and select all.
 */
@Dao
public interface ChoiceSynonymDao {

  /**
   * Method to insert a {@link ChoiceSynonym} into the database
   * @param choiceSynonym the synonym associated to a specific {@link edu.cnm.deepdive.picturequest.model.entity.Choice}
   * @author Brian Alexander
   */
  @Insert
  void insert(ChoiceSynonym choiceSynonym);

  /**
   * Method to insert multiple {@link ChoiceSynonym} at a time
   * @param choicesSynonyms the synonym('s) associated to a specific {@link edu.cnm.deepdive.picturequest.model.entity.Choice}
   */
  @Insert
  void insert(ChoiceSynonym... choicesSynonyms);

  /**
   * Method to select all {@link ChoiceSynonym} from the database at once
   * @return {@link LiveData}{@link List} of {@link ChoiceSynonym}
   */
  @Query("SELECT * FROM choicesynonym")
  LiveData<List<ChoiceSynonym>> getAll();

}
