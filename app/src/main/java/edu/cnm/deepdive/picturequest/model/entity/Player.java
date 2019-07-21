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

@Entity(
    foreignKeys = {
        @ForeignKey(entity = Scene.class,
            parentColumns = "id",
            childColumns = "scene_id",
            onDelete = ForeignKey.RESTRICT
        )
    },
    indices = {
        @Index(value = "authentication_id", unique = true)
    }
)
public class Player {


  @PrimaryKey(autoGenerate = true)
  private long id;
  @ColumnInfo(name = "authentication_id")
  private String authenticationId;
  private String player;
  @ColumnInfo(name = "scene_id", index = true)
  private long sceneId;

  public long getSceneId() {
    return sceneId;
  }

  public void setSceneId(long sceneId) {
    this.sceneId = sceneId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  public String getAuthenticationId() {
    return authenticationId;
  }

  public void setAuthenticationId(String authenticationId) {
    this.authenticationId = authenticationId;
  }
}
