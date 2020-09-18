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

import software.engineering.project1gradecalculator.model.AppDao;
import software.engineering.project1gradecalculator.model.Assignment;
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
public class AssignmentInstrumentedTests {

    private AppDao userDao;
    private RoomDB db;

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
    public void test_addAssignment(){
        Course newCourse = new Course("Dr. C", "CST438", "Learn software development", 12345);
        userDao.addCourse(newCourse);
        Assignment newAssignment = new Assignment(newCourse.getPrimaryKey(), 583763, 100, 80, "HW1");
        userDao.addAssignment(newAssignment);
        Assignment retrievedAssignment = userDao.getAssignmentByCourseID_and_AssignmentName(newCourse.getPrimaryKey(), "HW1");
        Assert.assertEquals("HW1", retrievedAssignment.getAssignmentName());
    }

    @Test
    public void test_deleteAssignment() {
        //create a temp course and add it to db
        Course newCourse = new Course("Dr. W", "CST338", "Learn software design", 54321);
        userDao.addCourse(newCourse);
        //create a new assignment
        Assignment newAssignment = new Assignment(newCourse.getPrimaryKey(), 993741, 100, 95, "Quiz1");
        userDao.addAssignment(newAssignment);

        Assignment retrievedAssignment = userDao.getAssignmentByCourseID_and_AssignmentName(newCourse.getPrimaryKey(), "Quiz1");
        userDao.deleteAssignment(retrievedAssignment);

        Assert.assertNull(userDao.getAssignmentByCourseID_and_AssignmentName(newCourse.getPrimaryKey(), "Quiz1"));
    }

    @Test
    public void test_updateAssignment(){

    }

    @Test
    public void test_getAllAssignmentsByCourse(){

    }

    @Test
    public void test_getAllAssignmentsByCategory(){

    }
}
