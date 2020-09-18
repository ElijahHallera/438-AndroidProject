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

    @Query("UPDATE User SET firstName = :newFirstName, lastName = :newLastName, password = :newPassword WHERE username = :currentUsername")
    void updateUser(String newFirstName, String newLastName, String newPassword, String currentUsername);

    //COURSE************************************************************
    @Query("select * from Course")
    List<Course> getAllCourses();

    @Insert void addCourse(Course course);

    @Query("select * from Course where title = :title")
    Course getCourseByName(String title);

    @Query("select * from Course where Username = :Username and title = :title")
    Course getCourseByUsername_and_Title(String Username, String title);
    //Assignment*********************************************************
    @Insert
    void addAssignment(Assignment assignment);

    @Delete
    void deleteAssignment(Assignment assignment);

    @Update
    void updateAssignment(Assignment assignment);

    @Query("select * from Assignment where courseID = :courseID")
    List<Assignment> getAllAssignmentsByCourse(int courseID);

    @Query("select * from Assignment where categoryID = :categoryID")
    List<Assignment> getAllAssignmentsByCategory(int categoryID);

    @Query("select * from Assignment where courseID = :courseID and assignmentName = :assignmentName")
    Assignment getAssignmentByCourseID_and_AssignmentName(int courseID, String assignmentName);
    //User Course*************************************************************
    @Insert
    void addUserCourse(User user, Course course);

    @Delete
    void deleteCourse(Course course);

    @Update
    void editCourse(Course course);

    @Query("select * from Course where username = :username")
    List<Course> userCourses(String username);

    //Category*****************************************************************
    @Insert
    void addCategory(Category category);

    @Query("select * from Category where courseID = :courseID")
    List<Category> courseCategories(int courseID);

    @Query("select * from Category where courseID = :courseID and name = :name")
    Category getCategoryByCourse_and_Name(int courseID, String name);
}
