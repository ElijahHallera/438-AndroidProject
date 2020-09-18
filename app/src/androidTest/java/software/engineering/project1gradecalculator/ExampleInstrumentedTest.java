package software.engineering.project1gradecalculator;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import software.engineering.project1gradecalculator.model.AppDao;
import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.User;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    final RoomDB new_db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build();

    @Test
    public void testing_tests(){
        User new_user = new User("Bobby", "Davis", "Bodavis", "otter");
        new_db.dao().addUser(new_user);
        Assert.assertEquals("Bodavis", new_db.dao().getUserByName("Bodavis").getUsername());
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("software.engineering.project1gradecalculator", appContext.getPackageName());
    }

}
