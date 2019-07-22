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
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.picturequest.model.entity.Scene;
import java.util.List;

/**
 * a {@link Dao} interface to interact with the {@link Scene} Entity of the data base, including methods
 * to insert one or many scenes into the data base, to get all scenes, find a specific scene based upon
 * its id that was generated in the database and to delete scenes from the database.
 */
@Dao
public interface SceneDao {

  /**
   * Method to insert a single {@link Scene} into the database
   * @param scene a single {@link Scene}
   * @return the id number of the {@link Scene}
   */
  @Insert
  long insert(Scene scene);

  /**
   * Method to insert zero or more {@link Scene}s into the database at a time.
   * @param scenes zero or more {@link Scene}
   * @return a {@link List} of {@link Scene} id's
   */
  @Insert
  List<Long> insert(Scene... scenes);

  /**
   * Currently unused method method to get all Scenes currently in the database.
   * @return a {@link LiveData} {@link List} of all {@link Scene}s
   */
  @Query("SELECT * FROM scene")
  LiveData<List<Scene>> getAll();

  /**
   * Method to get a specific {@link Scene} by its id in the database.
   * @param id the id of a {@link Scene}
   * @return a single {@link LiveData} {@link Scene} associated to the id put in
   */
  @Query("SELECT * FROM scene WHERE id = :id")
  LiveData<Scene> findById(long id);

  /**
   * Method to delete one or more {@link Scene} from the database.
   * @param scenes one or more {@link Scene} to be deleted.
   * @return an int to show if successful.
   */
  @Delete
  int delete(Scene... scenes);

}
