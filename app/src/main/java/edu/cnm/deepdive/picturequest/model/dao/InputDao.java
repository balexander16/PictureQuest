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
import edu.cnm.deepdive.picturequest.model.entity.Input;
import java.util.List;

/**
 * {@link Dao} Interface designed to interact with the {@link Input} Entity of the database,
 * containing method to Insert, get all {@link Input}, get t {@link Input} for a specific {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
 * and to delete {@link Input} stored for a specific {@link edu.cnm.deepdive.picturequest.model.entity.Player} and {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
 * @author Brian Alexander
 */
@Dao
public interface InputDao {

  /**
   * Method to insert a single {@link Input} into the database
   * @param input the {@link Input}
   */
  @Insert
  void insert(Input input);

  /**
   * Currently unused method designed to get all {@link Input} from the database
   * @return {@link LiveData}{@link List} of {@link Input}
   */
  @Query("SELECT * FROM  input")
  LiveData<List<Input>> getAll();

  /**
   * Method designed to get all inputs associated to a specific scene.
   * @param sceneId the id of a {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
   * @return a {@link LiveData} {@link List} of {@link String} of all {@link Input} to a specific {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
   */
  @Query("SELECT name FROM input WHERE scene_id = :sceneId")
  LiveData<List<String>> getAllByScene(long sceneId);                               //FIXME! Observe you first then put you in the next one

  /**
   * Method to delete the current {@link Input} for a {@link edu.cnm.deepdive.picturequest.model.entity.Player} for
   * a specifc {@link edu.cnm.deepdive.picturequest.model.entity.Scene} so that if the player comes back to that
   * {@link edu.cnm.deepdive.picturequest.model.entity.Scene} at any point, restarting or finishing a questline,
   * they will have the {@link Input} removed for them.
   * @param sceneId the id of a specific {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
   * @param playerId  the id of the {@link edu.cnm.deepdive.picturequest.model.entity.Player}
   */
  @Query("DELETE FROM Input WHERE scene_id = :sceneId AND player_id = :playerId")
  void deleteInputs(long sceneId, long playerId);


}
