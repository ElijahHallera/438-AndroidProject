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
import software.engineering.project1gradecalculator.model.Assignment;
import software.engineering.project1gradecalculator.model.Category;
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
        Course newCourse = new Course("Dr. C", "CST438", "Learn software development", 12345, "@test1", 85.0);
        userDao.addCourse(newCourse);
        Assignment newAssignment = new Assignment(newCourse.getPrimaryKey(), 583763, "homework" ,100, 80, "HW1");
        userDao.addAssignment(newAssignment);
        Assignment retrievedAssignment = userDao.getAssignmentByCourseID_and_AssignmentName(newCourse.getPrimaryKey(), "HW1");
        Assert.assertEquals("HW1", retrievedAssignment.getAssignmentName());
    }

    @Test
    public void test_deleteAssignment() {
        //create a temp course and add it to db
        Course newCourse = new Course("Dr. W", "CST338", "Learn software design", 54321, "@test2", 90.0);
        userDao.addCourse(newCourse);
        //create a new assignment
        Assignment newAssignment = new Assignment(newCourse.getPrimaryKey(), 993741, "quizzes" ,100, 95, "Quiz1");
        userDao.addAssignment(newAssignment);

        Assignment retrievedAssignment = userDao.getAssignmentByCourseID_and_AssignmentName(newCourse.getPrimaryKey(), "Quiz1");
        userDao.deleteAssignment(retrievedAssignment);

        Assert.assertNull(userDao.getAssignmentByCourseID_and_AssignmentName(newCourse.getPrimaryKey(), "Quiz1"));
    }

    @Test
    public void test_updateAssignment(){
        Course newCourse = new Course("Mr. X", "CST-RE2", "Learning", 92655, "@test3", 99.0);
        userDao.addCourse(newCourse);
        //create a new assignment
        Assignment newAssignment = new Assignment(newCourse.getPrimaryKey(), 842991, "tests" ,100, 65, "test1");
        userDao.addAssignment(newAssignment);

        Assignment retrievedAssignment = userDao.getAssignmentByCourseID_and_AssignmentName(newCourse.getPrimaryKey(), "test1");
        retrievedAssignment.setAssignmentName("test9");
        userDao.updateAssignment(retrievedAssignment);

        Assignment testAssignment = userDao.getAssignmentByCourseID_and_AssignmentName(newCourse.getPrimaryKey(), "test9");
        Assert.assertEquals("test9", testAssignment.getAssignmentName());
    }

    @Test
    public void test_getAllAssignmentsByCourse(){
        Course dummyCourse = new Course("Dr. Click", "Software Engineering", "Learning how to code", 438, "A@lice5", 0);
        userDao.addCourse(dummyCourse);
        Course addedCourse = userDao.getCourseByUsername_and_Title("A@lice5", "Software Engineering");

        Category homework = new Category(addedCourse.getPrimaryKey(), "homework", 40);
        Category tests = new Category(addedCourse.getPrimaryKey(), "tests", 40);
        Category quizzes = new Category(addedCourse.getPrimaryKey(), "quizzes", 20);
        userDao.addCategory(homework);
        userDao.addCategory(tests);
        userDao.addCategory(quizzes);
        Category addedHomework = userDao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "homework");
        Category addedTests = userDao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "tests");
        Category addedQuizzes = userDao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "quizzes");

        Assignment hw1 = new Assignment(addedCourse.getPrimaryKey(),
                addedHomework.getCategoryID(),
                addedHomework.getName(),
                100, 80, "HW1"
        );
        Assignment test1 = new Assignment(addedCourse.getPrimaryKey(),
                addedTests.getCategoryID(),
                addedTests.getName(),
                100, 95, "TEST1"
        );
        Assignment quiz1 = new Assignment(addedCourse.getPrimaryKey(),
                addedHomework.getCategoryID(),
                addedHomework.getName(),
                10, 7, "QUIZ1"
        );
        userDao.addAssignment(hw1);
        userDao.addAssignment(test1);
        userDao.addAssignment(quiz1);

        List<Assignment> temp = userDao.getAllAssignmentsByCourse(addedCourse.getPrimaryKey());
        Assert.assertEquals(3, temp.size());
    }

    @Test
    public void test_getAllAssignmentsByCategory(){
        Course dummyCourse = new Course("Dr. Click", "Software Engineering", "Learning how to code", 438, "A@lice5", 0);
        userDao.addCourse(dummyCourse);
        Course addedCourse = userDao.getCourseByUsername_and_Title("A@lice5", "Software Engineering");

        Category homework = new Category(addedCourse.getPrimaryKey(), "homework", 40);
        Category tests = new Category(addedCourse.getPrimaryKey(), "tests", 40);
        Category quizzes = new Category(addedCourse.getPrimaryKey(), "quizzes", 20);
        userDao.addCategory(homework);
        userDao.addCategory(tests);
        userDao.addCategory(quizzes);
        Category addedHomework = userDao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "homework");
        Category addedTests = userDao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "tests");
        Category addedQuizzes = userDao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "quizzes");

        Assignment hw1 = new Assignment(addedCourse.getPrimaryKey(),
                addedHomework.getCategoryID(),
                addedHomework.getName(),
                100, 80, "HW1"
        );
        Assignment test1 = new Assignment(addedCourse.getPrimaryKey(),
                addedTests.getCategoryID(),
                addedTests.getName(),
                100, 95, "TEST1"
        );
        Assignment quiz1 = new Assignment(addedCourse.getPrimaryKey(),
                addedQuizzes.getCategoryID(),
                addedQuizzes.getName(),
                10, 7, "QUIZ1"
        );
        userDao.addAssignment(hw1);
        userDao.addAssignment(test1);
        userDao.addAssignment(quiz1);

        List<Assignment> temp = userDao.getAllAssignmentsByCategory(quiz1.getCategoryID());
        Assert.assertEquals(1, temp.size());
    }

    @Test
    public void test_getAssignmentByCourseID_and_AssignmentName(){
        Course testCourse = new Course("Prof. X", "TestCourse", "Meant for testing", 789, "@Alice5", 99.0);
        userDao.addCourse(testCourse);
        Course retrievedCourse = userDao.getCourseByUsername_and_Title("@Alice5", "TestCourse");

        Category testCategory = new Category(retrievedCourse.getPrimaryKey(), "tests", 40);
        userDao.addCategory(testCategory);
        Category retrievedCategory = userDao.getCategoryByCourse_and_Name(retrievedCourse.getPrimaryKey(), "tests");

        Assignment testAssignment = new Assignment(retrievedCourse.getPrimaryKey(), retrievedCategory.getCategoryID(), testCategory.getName(), 100, 80, "test7");
        userDao.addAssignment(testAssignment);
        Assignment retrievedAssignment = userDao.getAssignmentByCourseID_and_AssignmentName(retrievedCourse.getPrimaryKey(), "test7");

        Assert.assertEquals("test7", retrievedAssignment.getAssignmentName());
    }
}
