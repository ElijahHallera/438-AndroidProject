package software.engineering.project1gradecalculator;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import software.engineering.project1gradecalculator.model.AppDao;
import software.engineering.project1gradecalculator.model.Category;
import software.engineering.project1gradecalculator.model.Course;
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
public class CourseIntrumentedTests {
    private AppDao userDao;
    private RoomDB db;
    private software.engineering.project1gradecalculator.model.Category Category;
    private Object List;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, RoomDB.class).build();
        userDao = db.dao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }
    @Test
    public void test_addCourse(){
        User u = new User("Jane", "Doe", "JD97", "pword970");
        userDao.addUser(u);
        Course c = new Course("Fraun", "SWE","Use TDD for SWE.", 93940, u.getUsername(), 90);
        userDao.addCourse(c);
        Assert.assertEquals("Fraun",c.getInstructor());
        Assert.assertEquals("SWE", c.getTitle());
        Assert.assertEquals("Use TDD for SWE.", c.getDescription());
        Assert.assertEquals(93940, c.getCourseID());
        Assert.assertEquals("JD97", c.getUsername());
        Assert.assertNotEquals("Belcher",c.getInstructor());
        Assert.assertNotEquals("Algos", c.getTitle());
        Assert.assertNotEquals("Design & analysis of algorithms.", c.getDescription());
        Assert.assertNotEquals(9343940, c.getCourseID());
        Assert.assertNotEquals("JWOW", c.getUsername());
    }

    @Test
    public void test_editCourse(){
        User u = new User("Jane", "Doe", "JD97", "pword970");
        userDao.addUser(u);
        Course c = new Course("Fraun", "SWE","Use TDD for SWE.", 93940, u.getUsername(), 90);
        userDao.addCourse(c);
        c.setInstructor("Byun");
        c.setTitle("Algos");
        c.setDescription("Design & analysis of algorithms.");
        c.setCourseID(9343940);
        userDao.editCourse(c);
        Assert.assertEquals("Byun",c.getInstructor());
        Assert.assertEquals("Algos", c.getTitle());
        Assert.assertEquals("Design & analysis of algorithms.", c.getDescription());
        Assert.assertEquals(9343940, c.getCourseID());
        Assert.assertEquals("JD97", c.getUsername());
        Assert.assertNotEquals("Belcher",c.getInstructor());
        Assert.assertNotEquals("SWE", c.getTitle());
        Assert.assertNotEquals("Use TDD for SWE.", c.getDescription());
        Assert.assertNotEquals(93940, c.getCourseID());
        Assert.assertNotEquals("JWOW", c.getUsername());
    }

    @Test
    public void test_userCourses(){
        User u = new User("Jane", "Doe", "JD97", "pword970");
        userDao.addUser(u);
        Course c = new Course("Fraun", "SWE","Use TDD for SWE.", 93940, u.getUsername(), 90);
        userDao.addCourse(c);
        List<Course> list = userDao.userCourses(u.getUsername());
        Assert.assertEquals("SWE",list.get(0).getTitle());
        Assert.assertNotEquals("Byun",list.get(0).getInstructor());

    }

    @Test
    public void test_getAllCourses(){
        User u = new User("Jane", "Doe", "JD97", "pword970");
        userDao.addUser(u);
        Course c = new Course("Fraun", "SWE","Use TDD for SWE.", 93940, u.getUsername(), 90);
        userDao.addCourse(c);
        List<Course> list = userDao.getAllCourses();
        Assert.assertEquals("SWE",list.get(0).getTitle());
        Assert.assertNotEquals("Byun",list.get(0).getInstructor());

    }

    @Test
    public void test_getCourseByNAME(){
        User u = new User("Jane", "Doe", "JD97", "pword970");
        userDao.addUser(u);
        Course c = new Course("Fraun", "SWE","Use TDD for SWE.", 93940, u.getUsername(), 90);
        userDao.addCourse(c);
        List<Course> list = userDao.getAllCourses();
        Assert.assertEquals("SWE",list.get(0).getTitle());
        Assert.assertNotEquals("Algos",list.get(0).getTitle());

    }

    @Test
    public void test_getCourseByUsername_and_Title(){
        User u = new User("Jane", "Doe", "JD97", "pword970");
        userDao.addUser(u);
        Course c = new Course("Fraun", "SWE","Use TDD for SWE.", 93940, u.getUsername(), 90);
        userDao.addCourse(c);
        Course c2 = userDao.getCourseByUsername_and_Title("JD97", "SWE");
        Assert.assertEquals("JD97",c2.getUsername());
        Assert.assertNotEquals("JWOW",c.getUsername());
        Assert.assertEquals("SWE",c2.getTitle());
        Assert.assertNotEquals("Algos",c2.getTitle());

    }
}
