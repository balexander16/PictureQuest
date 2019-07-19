package edu.cnm.deepdive.picturequest.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Choice;
import edu.cnm.deepdive.picturequest.service.ClarifaiTask;
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
