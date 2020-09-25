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
public class CategoryInstrumentedTests {

    private AppDao userDao;
    private RoomDB db;
    private Category Category;
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
    public void test_addCategory(){
        User user = new User("Jane", "Doe","JD97","pword111");
        userDao.addUser(user);
        Course newCourse = new Course("Dr. C", "CST438", "Learn software development", 12345, "JD97", 90);
        userDao.addCourse(newCourse);
        userDao.courseCategories(newCourse.getCourseID());
        Category newCategory = new Category(newCourse.getCourseID(), user.getFirstName(), 25);
        userDao.addCategory(newCategory);
        Category retrievedCategory = userDao.getCategoryByCourse_and_Name(newCourse.getCourseID(), user.getFirstName());
        Assert.assertEquals(12345, retrievedCategory.getCourseID());
        Assert.assertEquals("Jane", retrievedCategory.getName());
        Assert.assertNotEquals(232, retrievedCategory.getCourseID());
        Assert.assertNotEquals("Fraun", retrievedCategory.getName());
    }

    @Test
    public void test_courseCategoires(){
        User user = new User("Jane", "Doe","JD97","pword111");
        userDao.addUser(user);
        Course newCourse = new Course("Dr. C", "CST438", "Learn software development", 12345, "JD97", 90);
        userDao.addCourse(newCourse);
        userDao.courseCategories(newCourse.getCourseID());
        Category newCategory = new Category(newCourse.getCourseID(), user.getFirstName(), 50);
        userDao.addCategory(newCategory);
        List<Category> list = userDao.courseCategories(newCourse.getCourseID());
        Assert.assertEquals(12345,list.get(0).getCourseID());
        Assert.assertNotEquals(90232, list.get(0).getCourseID());

    }

    @Test
    public void test_getCategoryByCourse_and_Name(){
        User user = new User("Jane", "Doe","JD97","pword111");
        userDao.addUser(user);
        Course newCourse = new Course("Dr. C", "CST438", "Learn software development", 12345, "JD97", 90);
        userDao.addCourse(newCourse);
        userDao.courseCategories(newCourse.getCourseID());
        Category newCategory = new Category(newCourse.getCourseID(), user.getFirstName(), 15);
        userDao.addCategory(newCategory);
        Category category = userDao.getCategoryByCourse_and_Name(newCourse.getCourseID(), user.getFirstName());
        Assert.assertEquals(12345, category.getCourseID());
        Assert.assertEquals("Jane", category.getName());
        Assert.assertNotEquals(232, category.getCourseID());
        Assert.assertNotEquals("Fraun", category.getName());
    }
}