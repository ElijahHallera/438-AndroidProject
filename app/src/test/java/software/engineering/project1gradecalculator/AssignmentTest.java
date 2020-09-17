package software.engineering.project1gradecalculator;

import org.junit.Test;

import software.engineering.project1gradecalculator.model.Assignment;
import software.engineering.project1gradecalculator.model.Course;

import static org.junit.Assert.*;

public class AssignmentTest {

    @Test
    public void CreateAssignment() {
        Assignment new_assignment = new Assignment();
        new_assignment.setCourseID(336);
        new_assignment.setCategoryID(1);
        new_assignment.setMaxScore(100);
        new_assignment.setEarnedScore(95);
        new_assignment.setAssignmentName("PROJECT 1");

        assertEquals(336, new_assignment.getCourseID());
        assertNotEquals(123,  new_assignment.getCourseID());
        assertEquals(1, new_assignment.getCategoryID());
        assertNotEquals(5, new_assignment.getCategoryID());
        assertEquals(100, new_assignment.getMaxScore());
        assertNotEquals(50, new_assignment.getMaxScore());
        assertEquals(95, new_assignment.getEarnedScore());
        assertNotEquals(43, new_assignment.getEarnedScore());
        assertEquals("PROJECT 1", new_assignment.getAssignmentName());
        assertNotEquals("DEFINITELY NOT PROJECT 1",  new_assignment.getAssignmentName());
    }

}