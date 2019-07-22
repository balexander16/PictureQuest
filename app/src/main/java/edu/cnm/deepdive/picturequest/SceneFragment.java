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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.picturequest.model.entity.Scene;
import edu.cnm.deepdive.picturequest.viewmodel.SceneViewModel;


/**
 * {@link Fragment} class that holds a {@link ListView} of the current {@link Scene} displayed to the
 * user to play through the game. This is done by creating the {@link View} as well as the {@link SceneViewModel}
 * We then get the {@link #sceneId} from the {@link MainActivity} and use the {@link androidx.lifecycle.ViewModel} to
 * set the {@link Scene} which we then set into the {@link ListView}.
 */
public class SceneFragment extends Fragment {

  /**
   * The id of the {@link Scene} to be used.
   */
  private long sceneId;

  /**
   * Class that inflates the view and sets the {@link ListView} as described in the class description. 
   * @param inflater for the view
   * @param container container for the viewgroup
   * @param savedInstanceState Bundle sent to the fragment
   * @return the View
   */
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_scene, container, false);

    final SceneViewModel viewModel = ViewModelProviders.of(getActivity()).get(SceneViewModel.class);

    sceneId =((MainActivity)getActivity()).getSceneId();
    viewModel.setSceneId(sceneId);

    viewModel.getScene().observe(this, scene -> {
      final ArrayAdapter<Scene> adapter =
          new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,new Scene[] {scene});
          final ListView sceneListView = view.findViewById(R.id.scene_list);
          sceneListView.setAdapter(adapter);

    });

  return view;

  }

}
