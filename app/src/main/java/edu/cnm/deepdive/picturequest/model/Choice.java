package edu.cnm.deepdive.picturequest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id"},
    indices = {@Index("id")},
    foreignKeys = {
        @ForeignKey(entity = Scene.class,
            parentColumns = "id",
            childColumns = "from_scene_id"),
        @ForeignKey(entity = Scene.class,
            parentColumns = "id",
            childColumns = "to_scene_id")
    })
public class Choice {

  @PrimaryKey(autoGenerate = true)
  private Long id;
  private String name;
  @ColumnInfo(name = "from_scene_id", index = true)
  private String fromSceneId;
  @ColumnInfo(name = "to_scene_id", index = true)
  private String toStringId;

  public String getToStringId() {
    return toStringId;
  }

  public void setToStringId(String toStringId) {
    this.toStringId = toStringId;
  }

  public String getFromSceneId() {
    return fromSceneId;
  }

  public void setFromSceneId(String fromSceneId) {
    this.fromSceneId = fromSceneId;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
