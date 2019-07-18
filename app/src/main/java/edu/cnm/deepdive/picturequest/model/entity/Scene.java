package edu.cnm.deepdive.picturequest.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Scene {

  @PrimaryKey(autoGenerate = true)
  private long id;

  @NonNull
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

  @NonNull
  @Override
  public String toString() {
    return scene;
  }
}
