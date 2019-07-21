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
   * @return said {@link Player}
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Player player);

  
  @Query("SELECT * FROM player WHERE authentication_id = :authenticationId")
  Player get(String authenticationId);

  @Query("SELECT * FROM player")
  LiveData<List<Player>> getAll();

  @Query("SELECT * FROM player WHERE id = :id")
  LiveData<Player> findById(long id);

  @Update
  int update(Player... players);
}
