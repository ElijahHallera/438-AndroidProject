package software.engineering.project1gradecalculator.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities={User.class, Course.class, Assignment.class}, version=3, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    // singleton design
    private static RoomDB instance;

    public abstract AppDao dao();

    public static RoomDB getRoomDB(final Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class,
                    "RoomDB") // database name
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public void loadData(Context context){
        List<User> user_list = RoomDB.getRoomDB(context).dao().getAllUsers();
        if (user_list.size() == 0) {
            Log.d("RoomDB", "loading data ");
            loadUsers(context);
        }
    }

    //populate database with a few accounts
    private void loadUsers(Context context) {
        AppDao dao = getRoomDB(context).dao();

        User alice = new User("Alice", "Blue", "A@lice5", "@cSit100");
        User brian = new User("Brian", "Green", "$BriAn7","123aBc##");
        User chris = new User("Chris", "Yellow", "!chriS12!", "CHrIS12!!");
        dao.addUser(alice);
        dao.addUser(brian);
        dao.addUser(chris);
        Log.d("RoomDB", "3 users added to database");
    }

    //populate with courses and categories
    private void loadCourses(Context context) {
        AppDao dao = getRoomDB(context).dao();

        Course dummyCourse = new Course("Dr. Click", "Software Engineering",
                "Learning how to code", 438, "A@lice5");
        dao.addCourse(dummyCourse);
        Course addedCourse = dao.getCourseByUsername_and_Title("A@lice5", "Software Engineering");

        Category homework = new Category(addedCourse.getPrimaryKey(), "homework");
        Category tests = new Category(addedCourse.getPrimaryKey(), "tests");
        Category quizzes = new Category(addedCourse.getPrimaryKey(), "quizzes");
        dao.addCategory(homework);
        dao.addCategory(tests);
        dao.addCategory(quizzes);
        Category addedHomework = dao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "homework");
        Category addedTests = dao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "tests");
        Category addedQuizzes = dao.getCategoryByCourse_and_Name(addedCourse.getPrimaryKey(), "quizzes");

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
        dao.addAssignment(hw1);
        dao.addAssignment(test1);
        dao.addAssignment(quiz1);
    }
}