package software.engineering.project1gradecalculator;

import org.junit.Test;

import software.engineering.project1gradecalculator.model.User;

import static org.junit.Assert.*;

public class UserTest {

    //This Test checks if all setters and getters for User class works
    //Also tests if the credentials do not match up.
    @Test
    public void CreateUserReturnParameters() {
        User new_user = new User();
        new_user.setId(123456);
        new_user.setFirstName("Batman");
        new_user.setLastName("Robin");
        new_user.setUsername("Joker");
        new_user.setPassword("Bad");


        assertEquals("Batman", new_user.getFirstName());
        assertNotEquals("NotBatman", new_user.getFirstName());
        assertEquals("Robin", new_user.getLastName());
        assertNotEquals("Random_Name", new_user.getLastName());
        assertEquals("Joker", new_user.getUsername());
        assertNotEquals("Not_Username", new_user.getUsername());
        assertEquals("Bad", new_user.getPassword());
        assertNotEquals("Good_Password", new_user.getPassword());
        assertEquals(123456, new_user.getId());
        assertNotEquals(654321, new_user.getId());
    }
}