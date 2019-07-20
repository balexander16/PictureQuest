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
