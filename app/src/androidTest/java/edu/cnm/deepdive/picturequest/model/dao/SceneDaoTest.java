package edu.cnm.deepdive.picturequest.model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.SmallTest;
import edu.cnm.deepdive.picturequest.model.database.PictureQuestDatabase;
import edu.cnm.deepdive.picturequest.model.entity.Scene;
import edu.cnm.deepdive.picturequest.util.LiveDataTestUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runners.MethodSorters;


@SmallTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SceneDaoTest {

  private static PictureQuestDatabase db;
  private static SceneDao dao;
  private static long sceneId;

  @Rule
  public TestRule rule = new InstantTaskExecutorRule();


  @BeforeClass
  public static void setup() throws Exception {
    Context context = ApplicationProvider.getApplicationContext();
    db = Room.inMemoryDatabaseBuilder(context, PictureQuestDatabase.class).allowMainThreadQueries().build();
    dao = db.getSceneDao();
  }

  @Test
  public void insert() {
    Scene scene = new Scene();
    scene.setScene("Hello");
    sceneId = dao.insert(scene);
    assertTrue(sceneId > 0);
  }

  @Test(expected = SQLiteConstraintException.class)
  public void insertNullTitle() {
    Scene scene = new Scene();
    long id = dao.insert(scene);
  }

  @Test
  public void postInsertFindById() throws InterruptedException {
    Scene scene = LiveDataTestUtil.getValue(dao.findById(sceneId));
    assertNotNull(scene);
  }

  @AfterClass
  public static void tearDown() throws Exception {
    db.close();
  }

}