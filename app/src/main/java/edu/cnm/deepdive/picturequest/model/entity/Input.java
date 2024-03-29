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
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * This class is an {@link Entity} class for the {@link androidx.room.RoomDatabase} specifically for the
 * avaiable inputs to decide what choices you are presented in your choose your own adventure game. The {@link Entity} has {@link ForeignKey}
 * to the {@link Player} by their id, and to the {@link Scene} the game is currently at by id. Each Input has an id, name of what the
 * {@link edu.cnm.deepdive.picturequest.service.ClarifaiTask} found from the {@link edu.cnm.deepdive.picturequest.CameraFragment}'s picture turned
 * into a {@link java.util.List} of {@link String}s.
 * @author Brian Alexander
 */
@Entity
    (
    foreignKeys = {
        @ForeignKey(entity = Player.class,
            parentColumns = "id",
            childColumns = "player_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(entity = Scene.class,
            parentColumns = "id",
            childColumns = "scene_id",
            onDelete = ForeignKey.CASCADE)
    }
    )
public class Input {

  /**
   * The id of the {@link Input} autogenerated by {@link androidx.room.Room}
   */
  @PrimaryKey(autoGenerate = true)
  private long id;
  /**
   * The name of the {@link Input} as a String, the objects in a picture
   */
  @ColumnInfo(index = true, collate = ColumnInfo.NOCASE)
  @SerializedName("name")
  @Expose
  private String name;
  /**
   * The amount {@link edu.cnm.deepdive.picturequest.service.ClarifaiTask} receives from a {@link clarifai2.dto.model.output.ClarifaiOutput}
   * that it believes the likelihood of the predicted object is actually in the picture.
   * As of now unimplemented in the current version. Thought of constraining what inputs are used. Like only higher then 0.9 etc.
   * Left in for future versions.
   */
  private long value;
  /**
   * Date that the input is taken. Currently unimplemented but later want to include so easier to leave it in.
   */
  @TypeConverters(DateConverter.class)
  private Date date;
  /**
   * The id generated by {@link androidx.room.Room} for the {@link Player} associated to the {@link Input}
   */
  @ColumnInfo(name="player_id", index = true)
  private long playerId;
  /**
   * The id generated by {@link androidx.room.Room} for the {@link Scene} associated to the {@link Input}
   */
  @ColumnInfo(name="scene_id", index = true)
  private long sceneId;

  /**
   * Method to get the id of the {@link Player} associated to the {@link Input}
   * @return the id of the player
   */
  public long getPlayerId() {
    return playerId;
  }

  /**
   * Method to set the id of the {@link Player} for the {@link Input}
   * @param playerId the id to set the {@link Player} for the {@link Input}
   */
  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  /**
   * Method to get the id of the {@link Scene} for the {@link Input}
   * @return the id of the Scene.
   */
  public long getSceneId() {
    return sceneId;
  }

  /**
   * Method to set the id of the {@link Scene} for the {@link Input}
   * @param sceneId the id to set the {@link Scene} for the {@link Input}
   */
  public void setSceneId(long sceneId) {
    this.sceneId = sceneId;
  }

  /**
   * Unused method to get the Date for the Input
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * Unused method to set the date for the input
   * @param date of the input
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * Method to return the id of the {@link Input}
   * @return id of the input
   */
  public long getId() {
    return id;
  }

  /**
   * Method to set the id of the {@link Input}
   * @param id for the {@link Input}
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Method to get the name of the {@link Input}
   * @return the name of the {@link Input}
   */
  public String getName() {
    return name;
  }

  /**
   * Method to set the name of the {@link Input}
   * @param name name to set the name to.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Currently Unused method to get an {@link Input} balue
   * @return the value
   */
  public long getValue() {
    return value;
  }

  /**
   * Currently Unused method to set the value for an {@link Input}
   * @param value the {@link #value}
   */
  public void setValue(long value) {
    this.value = value;
  }

  /**
   * Currently unused class used to convert dates for when that we will be utilized.
   */
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
