package software.engineering.project1gradecalculator.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private String Instructor;

    @NonNull
    private String Title;

    @NonNull
    private String Description;

    @NonNull
    private int courseID;

    public Course(){}

    @Ignore
    public Course(String Instructor, String Title, String Description, int courseID){
        this.Instructor = Instructor;
        this.Title = Title;
        this.Description = Description;
        this.courseID = courseID;
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

    //******GETTERS******//
    @NonNull
    public String getInstructor() {
        return Instructor;
    }

    @NonNull
    public String getTitle() {
        return Title;
    }

    @NonNull
    public String getDescription() {
        return Description;
    }

    public int getCourseID() {
        return courseID;
    }
}
