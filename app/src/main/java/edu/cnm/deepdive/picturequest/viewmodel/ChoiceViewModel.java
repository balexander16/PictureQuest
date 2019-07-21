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

package edu.cnm.deepdive.picturequest.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Choice;
import java.util.List;

public class ChoiceViewModel extends AndroidViewModel {

  private LiveData<Choice> choice;


  public ChoiceViewModel(@NonNull Application application) {
    super(application);
  }

  public LiveData<List<String>> getInputs(long id) {
    return PictureQuestDatabase.getInstance(getApplication()).getInputDao().getAllByScene(id);
  }

  public LiveData<List<Choice>> getChoices(long id,
      List<String> objects) {
    return PictureQuestDatabase.getInstance(getApplication()).getChoiceDao()
        .getRelevantChoices(id, objects);
  }





}
