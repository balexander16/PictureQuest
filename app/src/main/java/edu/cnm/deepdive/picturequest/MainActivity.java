package edu.cnm.deepdive.picturequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import edu.cnm.deepdive.picturequest.service.GoogleSignInService;
import edu.cnm.deepdive.picturequest.viewmodel.MainViewModel;


public class MainActivity extends AppCompatActivity {

  private NavController navController;
  private BottomNavigationView bottomNav;
  private TextView mTextMessage;
  private long playerId;
  private long sceneId;
  private Player player;

  private MainViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setSceneId(getIntent().getLongExtra("scene_id", 1));
    playerId = getIntent().getLongExtra("player_id", 1);
    mTextMessage = findViewById(R.id.message);
    navController = Navigation.findNavController(this,R.id.nav_host_fragment);
    bottomNav = findViewById(R.id.navigation);
    NavigationUI.setupWithNavController(bottomNav, navController);

    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.setPlayerId(playerId);
    viewModel.getPlayer().observe(this, (player) -> {
      this.player = player;
      if (player.getSceneId() != sceneId) {
        setPlayerScene(sceneId, player);
      }
      clearSceneInputs(sceneId, player);
    });
  }

//  @Override
//  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//    boolean handled = true;
//    Bundle args = new Bundle();
//    switch (item.getItemId()) {
//      case R.id.story_home:
//        mTextMessage.setText(R.string.title_home);
//        break;
//      case R.id.navigation_choice:
//        mTextMessage.setText(R.string.title_dashboard);
//        break;
//      case R.id.navigation_camera:
//
//        navController.navigate(R.id.navigation_camera);
//        mTextMessage.setText(R.string.title_notifications);
//        break;
//      default:
//        handled = false;
//    }
//    return handled;
//  }

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

  public long getPlayerId() {
    return playerId;
  }

  public long getSceneId() {
    return sceneId;
  }

  public void setSceneId(long sceneId) {
    this.sceneId = sceneId;
    if (player != null) {
      setPlayerScene(sceneId, player);
      clearSceneInputs(sceneId, player);
    }
  }

  private void setPlayerScene(long sceneId, Player player) {
    player.setSceneId(sceneId);
    viewModel.updatePlayer(player);
  }

  private void clearSceneInputs(long sceneId, Player player) {
    viewModel.clearInputs(sceneId, player.getId());
  }
}
