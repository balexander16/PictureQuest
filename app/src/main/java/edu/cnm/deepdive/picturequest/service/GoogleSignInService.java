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

package edu.cnm.deepdive.picturequest.service;

import android.app.Application;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/**
 * The class is used to invoke a call to the Google Sign in OAuth client, a service class.
 */
public class GoogleSignInService {

  /**
   * Creating a private {@link GoogleSignInAccount}
   */
  private GoogleSignInAccount account;
  /**
   * Creating a loval private {@link GoogleSignInClient}
   */
  private GoogleSignInClient client;
  /**
   * Application context to be initialized in a setter.
   */
  private static Application context;

  /**
   * What we are requesting from the signInService. Upon using GoogleOAuth request the email, their
   * authentication id, and profile so we may access the name to add to {@link edu.cnm.deepdive.picturequest.model.entity.Player}
   */
  public GoogleSignInService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
        .build();
    client = GoogleSignIn.getClient(context, options);

  }

  /**
   * Set the current application context.
   * @param context the context to be set to
   */
  public static void setContext(Application context) {
    GoogleSignInService.context = context;
  }

  /**
   * Getter to get the GoogleSignIn client.
   * @return the client
   */
  public GoogleSignInClient getClient() {
    return client;
  }

  /**
   * Getter to get the account
   * @return the account
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**
   * Method to set the account during sign in.
   * @param account the account to be set to
   */
  public void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }

  /**
   * Getter to get the instance, for the {@link GoogleSignInService}
   * @return
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Class to hold the instance for us, each time a new sign in comes through we then use the instance for all of
   * the other initializations.
   */
  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();

  }

}
