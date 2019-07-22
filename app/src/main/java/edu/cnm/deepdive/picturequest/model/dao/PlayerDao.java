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
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import java.util.List;

/**
 * {@link Dao} Interface containing methods to insert, get a {@link Player} by authentication id,
 * find a {@link Player} by database id, and to update {@link Player}
 * @author Brian Alexander
 */
@Dao
public interface PlayerDao {

  /**
   * Method designed to insert a single {@link Player into the database.
   * @param player the {@link Player} to be inserted
   * @return the id of the {@link Player}
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Player player);

  /**
   * Method to get a {@link Player} from the database, based off of a unique authenticaiton id created during {@link edu.cnm.deepdive.picturequest.service.GoogleSignInService}
   * @param authenticationId authentication id created during {@link edu.cnm.deepdive.picturequest.service.GoogleSignInService} assigned by
   * Google, a unique id.
   * @return the {@link Player} associated to the unique id
   */
  @Query("SELECT * FROM player WHERE authentication_id = :authenticationId")
  Player get(String authenticationId);

  /**
   * Currently unused method to get all {@link Player}
   * @return a {@link LiveData} {@link List} of {@link Player}s
   */
  @Query("SELECT * FROM player")
  LiveData<List<Player>> getAll();

  /**
   * Method to select a {@link Player} based off of the id generated for them in the database.
   * @param id the id of a {@link Player} to get
   * @return the {@link Player} associated to the id
   */
  @Query("SELECT * FROM player WHERE id = :id")
  LiveData<Player> findById(long id);

  /**
   * Method to update the data for a {@link Player} entity already in the database.
   * @param players zero or more {@link Player}
   * @return one of more {@link Player} to be updated
   */
  @Update
  int update(Player... players);
}
