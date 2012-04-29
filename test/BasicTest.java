import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {
    
    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void createAndRetrieveUser() {
        String email = "kokos@gmail.com";
        String fullName = "Kokos";
        String password = "1234";
        // Create a new user and save it
        new User(email, password, fullName).save();

        // Retrieve the user with e-mail address bob@gmail.com
        User bob = User.find("byEmail", email).first();

        // Test 
        assertNotNull(bob);
        assertEquals(fullName, bob.fullname);
    }

}
