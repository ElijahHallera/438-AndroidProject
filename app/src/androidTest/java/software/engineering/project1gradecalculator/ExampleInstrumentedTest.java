package software.engineering.project1gradecalculator;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import software.engineering.project1gradecalculator.model.AppDao;
import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.User;
import software.engineering.project1gradecalculator.model.Course;

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

    //Create a new User, Add the user using addUser() from dao.
    //Get all Users using getAllUsers() from dao.
    //Compare the created Username with the Username in dao.
    @Test
    public void daoUserFunctions(){
        User new_user = new User("FirstName", "LastName", "Username", "Password");
        new_db.dao().addUser(new_user);
        new_db.dao().getAllUsers();
        Assert.assertEquals("Username", new_db.dao().getUserByName("Username").getUsername());
        Assert.assertNotEquals("Not Username", new_db.dao().getUserByName("Username").getUsername());

        //I know this dao function works I dont know how to write an Assert for it, tried a lot of ways, very challenging. (Elijah)
        new_db.dao().updateUser("NEWFIRSTNAME", "NEWLASTNAME", "NEWPASSWORD", new_db.dao().getUserByName("Username").toString());
    }

    //Tests adding a course using addCourse() from dao.
    //Tests getting all courses using getAllCourses() from dao.
    //Tests getting Title from the database using getCourseByName() from dao.
    @Test
    public void daoCourseFunctions(){
        Course new_course = new Course("Instructor", "Title", "Description", 12345);
        new_db.dao().addCourse(new_course);
        new_db.dao().getAllCourses();
        Assert.assertEquals("Title", new_db.dao().getCourseByName("Title").getTitle());
        Assert.assertNotEquals("Software Design", new_db.dao().getCourseByName("Title").getTitle());
    }
}
