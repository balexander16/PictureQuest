package edu.cnm.deepdive.picturequest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  //TODO Make a set of 1 or more buttons appear dependant upon inputs received through the clarifai api

}
