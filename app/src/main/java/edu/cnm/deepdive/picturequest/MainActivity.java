package edu.cnm.deepdive.picturequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;



  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.story_home:
          mTextMessage.setText(R.string.title_home);
          return true;
        case R.id.navigation_choice:
          mTextMessage.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_camera:
          mTextMessage.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mTextMessage = (TextView) findViewById(R.id.message);

    NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
    BottomNavigationView bottomNav = findViewById(R.id.navigation);
    NavigationUI.setupWithNavController(bottomNav, navController);

  }



//    protected void onCreate(Bundle savedInstanceState) {
//      super.onCreate(savedInstanceState);
//      setContentView(R.layout.fragment_camera);
//      if (null == savedInstanceState) {
//        getSupportFragmentManager().beginTransaction()
//            .replace(R.id.container, CameraFragment.newInstance())
//            .commit();
//      }
//    }



}
