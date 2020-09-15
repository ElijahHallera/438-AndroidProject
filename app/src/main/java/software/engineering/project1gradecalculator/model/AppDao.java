package software.engineering.project1gradecalculator.model;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AppDao {

    //USER********************************************************
    @Query("select * from User")
    List<User> getAllUsers();

    @Insert
    void addUser(User user);

    @Query("select * from User where username = :username")
    User getUserByName(String username);

    //COURSE************************************************************
    @Query("select * from Course")
    List<Course> getAllCourses();

    @Insert void addCourse(Course course);

    @Query("select * from Course where title = :title")
    Course getCourseByName(String title);

    //Assignment************************************************************
    @Insert
    void addAssignment(Assignment assignment);

    @Query("select * from Assignment where courseID = :courseID")
    Assignment getAllAssignmentsByCourse(int courseID);

}
