package edu.cnm.deepdive.picturequest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(primaryKeys =  {"id"},
    indices = {@Index("id")},
    foreignKeys = {
        @ForeignKey(entity = Player.class,
            parentColumns = "id",
            childColumns = "player_id"),
        @ForeignKey(entity = Scene.class,
            parentColumns = "id",
            childColumns = "to_scene_id"),
        @ForeignKey(entity = Choice.class,
            parentColumns = "id",
            childColumns = "choice_id")
    })
public class Input {

  @PrimaryKey(autoGenerate = true)
  private Long id;
  private String name;
  private Long value;
  private Long date;
  @ColumnInfo(name="player_id", index = true)
  private Long playerId;
  @ColumnInfo(name="choice_id", index = true)
  private Long choiceId;
  @ColumnInfo(name="scene_id", index = true)
  private Long sceneId;

  public Long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }

  public Long getChoiceId() {
    return choiceId;
  }

  public void setChoiceId(Long choiceId) {
    this.choiceId = choiceId;
  }

  public Long getSceneId() {
    return sceneId;
  }

  public void setSceneId(Long sceneId) {
    this.sceneId = sceneId;
  }

  public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }
}
