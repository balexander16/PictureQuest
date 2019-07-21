package edu.cnm.deepdive.picturequest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.picturequest.model.entity.Choice;
import edu.cnm.deepdive.picturequest.viewmodel.ChoiceViewModel;
import java.util.List;

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

    final View view = inflater.inflate(R.layout.fragment_choice, container, false);

    final ChoiceViewModel viewModel = ViewModelProviders.of(getActivity())
        .get(ChoiceViewModel.class);

    Button button = view.findViewById(R.id.button_choice_one);
    Button button2 = view.findViewById(R.id.button_choice_two);
    Button button3 = view.findViewById(R.id.button_final_choice);
    button3.setVisibility(View.INVISIBLE);

    if (((MainActivity) getActivity()).getSceneId() == 14
        || ((MainActivity) getActivity()).getSceneId() == 17) {
      button3.setVisibility(View.VISIBLE);
      button3.setText(getString(R.string.dead));
      button3.setOnClickListener((v) -> {
        goToFirstScene();
      });
    }

    if (((MainActivity) getActivity()).getSceneId() == 15
    || ((MainActivity) getActivity()).getSceneId() == 18) {
      button3.setVisibility(View.VISIBLE);
      button3.setText(getString(R.string.go_home));
      button3.setOnClickListener((v) -> {
        goToFirstScene();
      });
    }


    //TODO nest these inside of each other. list of strings first then the next one do do work off of it! aha!
    viewModel.getInputs(((MainActivity) getActivity()).getSceneId()).observe(this, input -> {
      viewModel.getChoices(((MainActivity) getActivity()).getSceneId(), input)
          .observe(this, choices -> {

            if (choices.isEmpty()) {
              button.setVisibility(View.INVISIBLE);
              button2.setVisibility(View.INVISIBLE);
            } else {
              button.setText(choices.get(0).toString());
              button.setVisibility(View.VISIBLE);
              button.setOnClickListener(
                  (v) -> {
                    goToNextScene(choices, 0);
                  });

              if (choices.size() > 1) {
                button2.setText(choices.get(1).toString());
                button2.setVisibility(View.VISIBLE);
                button2.setOnClickListener(
                    (v) -> {
                      goToNextScene(choices, 1);
                    });
              } else {
                button2.setVisibility(View.INVISIBLE);
              }
            }
          });
    });


    return view;
  }

  private void goToNextScene(List<Choice> choices, int i) {
    ((MainActivity) getActivity()).setSceneId(choices.get(i).getToSceneId());

    NavController navController = Navigation
        .findNavController(getActivity(), R.id.nav_host_fragment);
    navController.navigate(R.id.story_home);
  }

  private void goToFirstScene() {
    ((MainActivity) getActivity()).setSceneId(1);

    NavController navController = Navigation
        .findNavController(getActivity(), R.id.nav_host_fragment);
    navController.navigate(R.id.story_home);
  }

  //TODO set buttons display to be the available choices to the scene we are currently on and then onClick go to the next scene...........
  // TODO bundle?????

}
