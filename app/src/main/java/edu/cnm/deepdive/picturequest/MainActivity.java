package edu.cnm.deepdive.picturequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.service.GoogleSignInService;


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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.sign_out:
        signOut();
        break;
        default:
          handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  private void  signOut() {
    GoogleSignInService service = GoogleSignInService.getInstance();
    service.getClient().signOut().addOnCompleteListener((task) -> {
      service.setAccount(null);
      Intent intent = new Intent(this, LoginActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
  }


}
