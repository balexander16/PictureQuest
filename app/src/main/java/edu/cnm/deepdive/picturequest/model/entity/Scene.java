package edu.cnm.deepdive.picturequest.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Scene {

  @PrimaryKey(autoGenerate = true)
  private long id;

  private String scene;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getScene() {
    return scene;
  }

  public void setScene(String scene) {
    this.scene = scene;
  }
}
