package edu.cnm.deepdive.picturequest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.picturequest.viewmodel.ChoiceViewModel;

public class ChoiceFragment extends Fragment {

  public ChoiceFragment() {
    // required empty constructor
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    Bundle args = getArguments();
    long playerId = args.getLong("player_id");
    long sceneId = args.getLong("scene_id");


    final View view = inflater.inflate(R.layout.fragment_choice, container, false);

    final ChoiceViewModel viewModel = ViewModelProviders.of(getActivity()).get(ChoiceViewModel.class);


//    viewModel.getChoice(sceneId)









    return view;
  }

  //TODO set buttons display to be the available choices to the scene we are currently on and then onClick go to the next scene...........
  // TODO bundle?????

}
