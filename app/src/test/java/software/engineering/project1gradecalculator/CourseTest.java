package software.engineering.project1gradecalculator;

import org.junit.Test;

import software.engineering.project1gradecalculator.model.Course;

import static org.junit.Assert.*;

public class CourseTest {

    //This Test checks if all setters and getters for Course class works
    //Also tests if the credentials do not match up.
    @Test
    public void CreateCourseReturnParameters() {
        Course new_course = new Course();
        new_course.setInstructor("Dr.C");
        new_course.setTitle("Software Engineering");
        new_course.setDescription("Creating Apps");
        new_course.setCourseID(438);

        assertEquals("Dr.C", new_course.getInstructor());
        assertNotEquals("Dr. Byun", new_course.getInstructor());
        assertEquals("Software Engineering", new_course.getTitle());
        assertNotEquals("Arts & Crafts", new_course.getTitle());
        assertEquals("Creating Apps", new_course.getDescription());
        assertNotEquals("Solving Math Equations", new_course.getDescription());
        assertEquals(438, new_course.getCourseID());
        assertNotEquals(300, new_course.getCourseID());
    }
}