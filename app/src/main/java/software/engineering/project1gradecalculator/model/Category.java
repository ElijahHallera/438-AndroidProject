package software.engineering.project1gradecalculator.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int categoryID;
    @NonNull
    private int courseID;   //(this is pk in Course.class)
    @NonNull
    private String name;
    private double score;

    public Category(){}

    @Ignore
    public Category(int courseID, @NonNull String name) {
        this.courseID = courseID;
        this.name = name;
    }

    //getters
    public int getCategoryID() { return categoryID; }
    public int getCourseID() { return courseID; }
    @NonNull
    public String getName() { return name; }
    public double getScore() { return score; }

    //setters
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }
    public void setCourseID(int courseID) { this.courseID = courseID; }
    public void setName(@NonNull String name) { this.name = name; }
    public void setScore(double score) { this.score = score; }

    @Override
    public String toString() {
        return name + "   TOTAL: " +score;
    }
}
