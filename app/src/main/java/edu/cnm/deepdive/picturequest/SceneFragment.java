package edu.cnm.deepdive.picturequest;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.picturequest.model.entity.Scene;
import edu.cnm.deepdive.picturequest.service.ClarifaiTask;
import edu.cnm.deepdive.picturequest.viewmodel.SceneViewModel;

public class SceneFragment extends Fragment {


  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_scene, container, false);

    final SceneViewModel viewModel = ViewModelProviders.of(getActivity()).get(SceneViewModel.class);


    // TODO change the arguments based upon the scene and pass this around?
    Bundle args = getArguments();
    long sceneId = args.getLong("scene_id", 1);
    args.putLong("scene_id", sceneId);
    long playerId = args.getLong("player_id", 1);
    args.putLong("player_id", playerId);

    ChoiceFragment choice = new ChoiceFragment();
    choice.setArguments(args);

    CameraFragment camera = new CameraFragment();
    camera.setArguments(args);


    viewModel.getScene(sceneId).observe(this, scene -> {
      final ArrayAdapter<Scene> adapter =
          new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,new Scene[] {scene});
          final ListView sceneListView = view.findViewById(R.id.scene_list);
          sceneListView.setAdapter(adapter);

    });

  return view;

  }

  //TODO display current scene we are on.


}
