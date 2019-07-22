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

package edu.cnm.deepdive.picturequest.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 * This class is an {@link Entity} class for the {@link androidx.room.RoomDatabase} specifically for the
 * avaiable scenes for the 'choose' your own adventure game. This has no foreign keys. This Entity is the scene
 * of a text based adventure game. These have an id, the scene itself as a String and the toString is overridden
 * to just return the 'name' which is the text of the story.
 * @author Brian Alexander
 */
@Entity
public class Scene {

  /**
   * The id of the {@link Scene} autogenerated by {@link androidx.room.Room}
   */
  @PrimaryKey(autoGenerate = true)
  private long id;
  /**
   * The scene itself which is a {@link String} created in a raw resource {@link com.google.gson.JsonArray}
   * preloaded in {@link edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase}
   */
  @NonNull
  private String scene;

  /**
   * Method to get the id of the {@link Scene}
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Method to set a {@link Scene} id
   * @param id the {@link #id}
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Method to get the {@link String} of the Scene
   * @return the Scene
   */
  public String getScene() {
    return scene;
  }

  /**
   * Method to set the Scene
   * @param scene what to set a scene as.
   */
  public void setScene(String scene) {
    this.scene = scene;
  }

  @NonNull
  @Override
  public String toString() {
    return scene;
  }
}
