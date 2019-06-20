package edu.cnm.deepdive.picturequest.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import java.util.Date;

@Entity(
    foreignKeys = {
        @ForeignKey(entity = Player.class,
            parentColumns = "id",
            childColumns = "player_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(entity = Scene.class,
            parentColumns = "id",
            childColumns = "scene_id",
            onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Choice.class,
            parentColumns = "id",
            childColumns = "choice_id",
            onDelete = ForeignKey.CASCADE)
    })
public class Input {

  @PrimaryKey(autoGenerate = true)
  private long id;
  @ColumnInfo(index = true, collate = ColumnInfo.NOCASE)
  private String name;
  private long value;
  @TypeConverters(DateConverter.class)
  private Date date;
  @ColumnInfo(name="player_id", index = true)
  private long playerId;
  @ColumnInfo(name="choice_id", index = true)
  private long choiceId;
  @ColumnInfo(name="scene_id", index = true)
  private long sceneId;

  public long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  public long getChoiceId() {
    return choiceId;
  }

  public void setChoiceId(long choiceId) {
    this.choiceId = choiceId;
  }

  public long getSceneId() {
    return sceneId;
  }

  public void setSceneId(long sceneId) {
    this.sceneId = sceneId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

  public static class DateConverter {

    @TypeConverter
    public static Date longToDate(Long value){
      return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToLong(Date value){
      return value == null ? null : value.getTime();
    }

  }
}
