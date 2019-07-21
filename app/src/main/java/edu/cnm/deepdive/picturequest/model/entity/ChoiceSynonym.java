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
import com.google.gson.annotations.SerializedName;

@Entity(
    foreignKeys = {
        @ForeignKey(entity = Choice.class,
            parentColumns = "id",
            childColumns = "choice_id",
            onDelete = ForeignKey.CASCADE)
    }
    )
public class ChoiceSynonym {

  @PrimaryKey(autoGenerate = true)
  private long id;
  private String name;
//  @ColumnInfo(name="synonym_name", index = true)          Don't need these at all....
//  private String synonymName;
  @SerializedName("to")
  @ColumnInfo(name="choice_id", index = true)
  private long choiceId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getChoiceId() {
    return choiceId;
  }

  public void setChoiceId(long choiceId) {
    this.choiceId = choiceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

//  public String getSynonymName() {
//    return synonymName;                           Dont think I need these at all.....
//  }
//
//  public void setSynonymName(String synonymName) {
//    this.synonymName = synonymName;
//  }
}
