package edu.cnm.deepdive.picturequest;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.picturequest.model.entity.Scene;
import edu.cnm.deepdive.picturequest.viewmodel.SceneViewModel;
import java.util.ArrayList;
import java.util.List;

public class SceneFragment extends Fragment {

  private Context context;

  public SceneFragment() {
    // required empty constructor
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_scene, container, false);

    final SceneViewModel viewModel = ViewModelProviders.of(getActivity()).get(SceneViewModel.class);

    viewModel.getSceneLiveData().observe(this, scene -> {
      final ArrayAdapter<Scene> adapter =
          new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, scene);
          final ListView sceneListView = view.findViewById(R.id.scene_list);
          sceneListView.setAdapter(adapter);

    });


  return view;

  }






}
