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

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(
    indices = {
        @Index(value = {"from_scene_id","name"}, unique = true)},
    foreignKeys = {
        @ForeignKey(entity = Scene.class,
            parentColumns = "id",
            childColumns = "from_scene_id",
            onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Scene.class,
            parentColumns = "id",
            childColumns = "to_scene_id",
            onDelete = ForeignKey.CASCADE)
    }
    )
public class Choice {

  @PrimaryKey(autoGenerate = true)
  private long id;
  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String name;
  @ColumnInfo(name = "from_scene_id", index = true)
  @SerializedName("from")
  private long fromSceneId;
  @SerializedName("to")
  @ColumnInfo(name = "to_scene_id", index = true)
  private long toSceneId;

  public long getToSceneId() {
    return toSceneId;
  }

  public void setToSceneId(long toSceneId) {
    this.toSceneId = toSceneId;
  }

  public long getFromSceneId() {
    return fromSceneId;
  }

  public void setFromSceneId(long fromSceneId) {
    this.fromSceneId = fromSceneId;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  // TODO Add another field to add some descriptive text into the choice scene.
  //        Such as walk towards the wagon etc.... if necessary.


  @Override
  public String toString() {
    return name;
  }
}
