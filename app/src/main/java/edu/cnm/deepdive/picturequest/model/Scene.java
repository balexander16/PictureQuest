package edu.cnm.deepdive.picturequest.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Scene {

  @PrimaryKey(autoGenerate = true)
  private Long id;

  private String scene;

  public Long getId() {
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
