package edu.cnm.deepdive.picturequest.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
    foreignKeys = {
        @ForeignKey(entity = Choice.class,
            parentColumns = "id",
            childColumns = "choice_id",
            onDelete = ForeignKey.CASCADE)
    })
public class ChoiceSynonym {

  @PrimaryKey(autoGenerate = true)
  private long id;
  private String name;
  @ColumnInfo(name="synonym_name", index = true)
  private String synonymName;
  @ColumnInfo(name="choice_id", index = true)
  private String choiceId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getChoiceId() {
    return choiceId;
  }

  public void setChoiceId(String choiceId) {
    this.choiceId = choiceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSynonymName() {
    return synonymName;
  }

  public void setSynonymName(String synonymName) {
    this.synonymName = synonymName;
  }
}