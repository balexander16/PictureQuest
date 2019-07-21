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

@Dao
public interface SceneDao {

  @Insert
  long insert(Scene scene);

  @Insert
  List<Long> insert(Scene... scenes);

  @Query("SELECT * FROM scene")
  LiveData<List<Scene>> getAll();

  @Query("SELECT * FROM scene WHERE id = :id")
  LiveData<Scene> findById(long id);

  @Delete
  int delete(Scene... scenes);

}
