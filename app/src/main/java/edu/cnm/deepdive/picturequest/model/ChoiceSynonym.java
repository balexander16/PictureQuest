package edu.cnm.deepdive.picturequest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(primaryKeys =  {"id"},
    indices = {@Index("id")},
    foreignKeys = {
        @ForeignKey(entity = Choice.class,
            parentColumns = "id",
            childColumns = "choice_id")
    })
public class ChoiceSynonym {

  private String name;
  @ColumnInfo(name="synonym_name", index = true)
  private String synonymName;
  @ColumnInfo(name="choice_id", index = true)
  private String choiceId;

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
