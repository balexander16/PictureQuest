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



package edu.cnm.deepdive.picturequest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

/**
 * {@link Fragment} class that is navigated to through the {@link MainActivity} the purpose of this
 * {@link Fragment} is to display {@link Button}s that have the associated {@link Choice} to continue the
 * Story of the game then upon clicking a {@link Button} navigate you to the {@link SceneFragment} with the
 * next {@link edu.cnm.deepdive.picturequest.model.entity.Scene} displayed.
 */
public class ChoiceFragment extends Fragment {

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }

  /**
   * Override of the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} method to create all of our
   * {@link android.text.Layout} and {@link ChoiceViewModel} as well as create all {@link Button} objects for the specific {@link Choice}s
   * This is done in a nested call to the {@link ChoiceViewModel#getInputs(long)} to see what the inputs are and then to put those inputs
   * into {@link ChoiceViewModel#getChoices(long, List)} which queries to see if any of the {@link edu.cnm.deepdive.picturequest.model.entity.Input}
   * Match a {@link Choice} for the current {@link edu.cnm.deepdive.picturequest.model.entity.Scene} by its id
   * @param inflater the layout inflater
   * @param container viewgroup
   * @param savedInstanceState the bundle instance state of the app
   * @return {@link View}
   */
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

  /**
   * Method to go to the next {@link edu.cnm.deepdive.picturequest.model.entity.Scene} according to the button that is clicked.
   * @param choices
   * @param i
   */
  private void goToNextScene(List<Choice> choices, int i) {
    ((MainActivity) getActivity()).setSceneId(choices.get(i).getToSceneId());

    NavController navController = Navigation
        .findNavController(getActivity(), R.id.nav_host_fragment);
    navController.navigate(R.id.story_home);
  }

  /**
   * Method to return the game state to the first {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
   */
  private void goToFirstScene() {
    ((MainActivity) getActivity()).setSceneId(1);

    NavController navController = Navigation
        .findNavController(getActivity(), R.id.nav_host_fragment);
    navController.navigate(R.id.story_home);
  }

}
