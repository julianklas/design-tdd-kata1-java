import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sls.domain.time.FixedTimeService;
import sls.domain.User;

import java.time.LocalDateTime;

public class UserTests {

    @Test
    public void testUserCanBeCreated() {
        // Given: a user
        User user = new User("John", new FixedTimeService(LocalDateTime.now()));

        // Then: we have a user which can be greeted
        Assert.assertEquals("John", user.getName() );
    }


}
