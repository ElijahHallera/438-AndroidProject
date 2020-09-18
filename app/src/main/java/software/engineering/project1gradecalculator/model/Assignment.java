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
    private int maxScore;
    @NonNull
    private int earnedScore;
    @NonNull
    private String assignmentName;

    public Assignment(){}

    @Ignore
    public Assignment(int courseID, int categoryID, int maxScore, int earnedScore, String assignmentName) {
        this.courseID = courseID;
        this.categoryID = categoryID;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignmentName = assignmentName;
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

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(int earnedScore) {
        this.earnedScore = earnedScore;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }


}
