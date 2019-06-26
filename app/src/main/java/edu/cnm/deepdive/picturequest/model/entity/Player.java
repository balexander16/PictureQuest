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
    })
public class Player {


  @PrimaryKey(autoGenerate = true)
  private long id;
  private String player;
  @ColumnInfo(name = "scene_id", index = true)
  private String sceneId;

  public String getSceneId() {
    return sceneId;
  }

  public void setSceneId(String sceneId) {
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
}
