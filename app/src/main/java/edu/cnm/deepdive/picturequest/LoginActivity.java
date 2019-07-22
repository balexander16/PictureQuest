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
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Player;
import edu.cnm.deepdive.picturequest.service.GoogleSignInService;

/**
 * Initial {@link android.app.Activity} to have a person login using {@link GoogleSignInService}
 * If someone is already signed in it will load the {@link MainActivity} with a {@link Player} already
 * associated to the sign in and set the {@link edu.cnm.deepdive.picturequest.model.entity.Scene} to
 * the last place they left the activity at.
 */
public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    findViewById(R.id.sign_in).setOnClickListener((view) -> signIn());
  }

  @Override
  protected void onStart() {
    super.onStart();
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if (account != null) {
      GoogleSignInService.getInstance().setAccount(account);
      new LoadPlayerTask().execute();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == LOGIN_REQUEST_CODE) {
      try {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        GoogleSignInAccount account = task.getResult(ApiException.class);
        GoogleSignInService.getInstance().setAccount(account);
        new PopulatePlayerTask().execute();
      } catch (ApiException e) {
        Toast.makeText(this, getString(R.string.authentication_error), Toast.LENGTH_LONG).show();
      }
    }
  }

  private void signIn() {
    Intent intent = GoogleSignInService.getInstance().getClient().getSignInIntent();
    startActivityForResult(intent, LOGIN_REQUEST_CODE);
  }

  /**
   * Switch to the main activity with a {@link Player} and {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
   * associated to them.
   * @param playerId
   * @param sceneId
   */
  private void switchToMain(long playerId, long sceneId) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.putExtra("player_id", playerId);
    intent.putExtra("scene_id", sceneId);
    startActivity(intent);
  }

  /**
   * Class to populate the {@link androidx.room.RoomDatabase} with a {@link Player} based upon inputs from the
   * {@link GoogleSignInService} as well as setting their {@link edu.cnm.deepdive.picturequest.model.entity.Scene}
   */
  private class PopulatePlayerTask extends AsyncTask<Void, Void, Player> {

  @Override
    protected Player doInBackground(Void... voids) {
      GoogleSignInAccount account = GoogleSignInService.getInstance().getAccount();
      PictureQuestDatabase db = PictureQuestDatabase.getInstance(LoginActivity.this);
      Player player = db.getPlayerDao().get(account.getId());
      if (player == null) {
        player = new Player();
        player.setAuthenticationId(account.getId());
        player.setPlayer(account.getDisplayName());
        player.setSceneId(1);
        long id = db.getPlayerDao().insert(player);
        player.setId(id);
      }
      return player;
    }

    @Override
    protected void onPostExecute(Player player) {
      switchToMain(player.getId(), player.getSceneId());
    }

  }

  /**
   * Class to load the game for a player that is already signed in.
   */
  private class LoadPlayerTask extends AsyncTask<Void, Void, Player> {

    @Override
    protected Player doInBackground(Void... voids) {
      GoogleSignInAccount account = GoogleSignInService.getInstance().getAccount();
      PictureQuestDatabase db = PictureQuestDatabase.getInstance(LoginActivity.this);
      Player player = db.getPlayerDao().get(account.getId());

      return player;
    }

    @Override
    protected void onPostExecute(Player player) {
      switchToMain(player.getId(), player.getSceneId());
    }
  }

}
