package edu.cnm.deepdive.picturequest.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Choice;
import edu.cnm.deepdive.picturequest.service.ClarifaiTask;
import java.util.List;

public class ChoiceViewModel extends AndroidViewModel {

  private LiveData<Choice> choice;

  public ChoiceViewModel(@NonNull Application application) {
    super(application);
  }

    public LiveData<List<Choice>> getChoice(long id, List<String> objects) {                                                  /*what can I put in here????*/
      return PictureQuestDatabase.getInstance(getApplication()).getChoiceDao().getRelevantChoices(id, objects);         //TODO aaaaaaaaaaaaaaaaaaaaaah
  }
}
