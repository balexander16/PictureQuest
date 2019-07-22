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

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.service.GoogleSignInService;

/**
 * {@link Application} class to create, Initialize {@link Stetho} and do a empty delete of
 * on the {@link androidx.room.RoomDatabase} to initialize the database upon app creation, and finally
 * initialize the {@link GoogleSignInService}
 */
public class PictureQuestApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    new Thread(() -> {
      PictureQuestDatabase.getInstance(this).getSceneDao().delete();
    }).start();
    GoogleSignInService.setContext(this);
  }


}
