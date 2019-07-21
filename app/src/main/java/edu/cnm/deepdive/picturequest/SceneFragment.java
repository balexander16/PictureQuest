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

public class SceneFragment extends Fragment {

  private long sceneId;

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_scene, container, false);

    final SceneViewModel viewModel = ViewModelProviders.of(getActivity()).get(SceneViewModel.class);

    sceneId =((MainActivity)getActivity()).getSceneId();
    viewModel.setSceneId(sceneId);
  // TODO

    viewModel.getScene().observe(this, scene -> {
      final ArrayAdapter<Scene> adapter =
          new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,new Scene[] {scene});
          final ListView sceneListView = view.findViewById(R.id.scene_list);
          sceneListView.setAdapter(adapter);

    });

  return view;

  }

  //TODO display current scene we are on......


}
