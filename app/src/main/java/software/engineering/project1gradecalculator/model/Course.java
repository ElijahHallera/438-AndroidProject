package software.engineering.project1gradecalculator.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    int pk;

    @NonNull
    private String Username;

    @NonNull
    private int courseID;

    @NonNull
    private String Title;

    @NonNull
    private String Description;

    @NonNull
    private String Instructor;

    private float grade;

    public Course(){}

    @Ignore
    public Course(String Instructor, String Title, String Description, int courseID, String Username, float grade){
        this.Instructor = Instructor;
        this.Title = Title;
        this.Description = Description;
        this.courseID = courseID;
        this.Username = Username;
        this.grade = grade;
    }

    //******SETTERS******//
    public void setInstructor(@NonNull String instructor) {
        Instructor = instructor;
    }

    public void setTitle(@NonNull String title) {
        Title = title;
    }

    public void setDescription(@NonNull String description) {
        Description = description;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setUsername(String username) { this.Username = username ;}

    public void setGrade(float grade) { this.grade = grade; }

    //******GETTERS******//
    @NonNull
    public String getInstructor() {
        return Instructor;
    }

    //    @NonNull
    public String getTitle() {
        return Title;
    }

    @NonNull
    public String getDescription() {
        return Description;
    }

    @NonNull
    public String getUsername() {
        return Username;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getPrimaryKey() {return pk;}

    public float getGrade() { return grade; }

    @Override
    public String toString() {
        return "Course {" +
                "Course ID: " + courseID +
                ", Title: '" + Title + '\'' +
                ", Description: '" + Description + '\'' +
                ", Instructor: '" + Instructor + '\'' +
                '}';
    }
}
