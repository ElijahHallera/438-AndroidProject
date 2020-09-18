package software.engineering.project1gradecalculator.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Assignment {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int assignmentID;
    @NonNull
    private int courseID;   //pk in course.class
    @NonNull
    private int categoryID;
    @NonNull
    private double maxScore;
    @NonNull
    private double earnedScore;
    @NonNull
    private String assignmentName;
    @NonNull
    private String categoryName;

    public Assignment(){}

    @Ignore
    public Assignment(int courseID, int categoryID, String categoryName, double maxScore, double earnedScore, String assignmentName) {
        this.courseID = courseID;
        this.categoryID = categoryID;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignmentName = assignmentName;
        this.categoryName = categoryName;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public double getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(double earnedScore) {
        this.earnedScore = earnedScore;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getCategoryName(){ return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    @Override
    public String toString() {
        return assignmentName + "   "+ categoryName + "   " + earnedScore + "/" + maxScore + '\'' +
                '}';
    }
}
