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

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import edu.cnm.deepdive.picturequest.service.GoogleSignInService;
import edu.cnm.deepdive.picturequest.viewmodel.MainViewModel;

/**
 *   Main Activity for the class, containing the onCreate class, and calls a MainViewModel. The MainActivity
 *   is used to hold the current sceneId and playerId values that are needed in the SceneFragment, CameraFragment and
 *   ChoiceFragment.
 */
public class MainActivity extends AppCompatActivity {

  private NavController navController;
  private BottomNavigationView bottomNav;
  private TextView mTextMessage;
  private long playerId;
  private long sceneId;
  private Player player;

  private MainViewModel viewModel;

  /**
   * Protected onCreate Override to create the NavController and to get and set the playerId and
   * sceneId parameters. As well as initializing the MainViewModel. Call to MainViewModel
   * is used to Observe when a new player is created and to set the scene for a new player to te first
   * scene.
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setSceneId(getIntent().getLongExtra("scene_id", 1));
    playerId = getIntent().getLongExtra("player_id", 1);
    mTextMessage = findViewById(R.id.message);
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    bottomNav = findViewById(R.id.navigation);
    NavigationUI.setupWithNavController(bottomNav, navController);
    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.setPlayerId(playerId);
    viewModel.getPlayer().observe(this, (player) -> {
      if (player != null) {
        this.player = player;
        if (player.getSceneId() != sceneId) {
          setPlayerScene(sceneId, player);
        }
        clearSceneInputs(sceneId, player);
      }
    });
  }

  /**
   * Creates and inflates the options menu.
   * @param menu
   * @return
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  /**
   * Currently contains the only option in the options menu, to sign out.
   * @param item
   * @return
   */
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
  //TODO create a reset option in the options menu!

  /**
   * Initialized a GoogleSignInService and sets the account to null, i.e. signing out.
   */
  private void signOut() {
    GoogleSignInService service = GoogleSignInService.getInstance();
    service.getClient().signOut().addOnCompleteListener((task) -> {
      service.setAccount(null);
      Intent intent = new Intent(this, LoginActivity.class);
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
    });
  }

  /**
   * Getter to get the playerId
   * @return playerId
   */
  public long getPlayerId() {
    return playerId;
  }

  /**
   * Getter to get the sceneId
   * @return sceneId
   */
  public long getSceneId() {
    return sceneId;
  }

  /**
   * Setter to set the current scene for the player, as long as there is a player,
   * as clearing any inputs that have already been used for that scene by the current player if they
   * have been to that scene before.
   * @param sceneId
   */
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
