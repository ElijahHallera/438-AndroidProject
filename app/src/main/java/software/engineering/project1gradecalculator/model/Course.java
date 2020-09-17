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
    private int courseID;

    @NonNull
    private String Title;

    @NonNull
    private String Description;

    @NonNull
    private String Instructor;

    public Course(){}

    @Ignore
    public Course(String Instructor, String Title, String Description, int courseID){
        this.Instructor = Instructor;
        this.Title = Title;
        this.Description = Description;
        this.courseID = courseID;
    }

    //******SETTERS******//
    public void setInstructor(String instructor) {
        this.Instructor = instructor;
    }

    public void setTitle(String title) { this.Title = title; }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    //******GETTERS******//
    public String getInstructor() {
        return Instructor;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getPrimaryKey() {return pk;}
}
