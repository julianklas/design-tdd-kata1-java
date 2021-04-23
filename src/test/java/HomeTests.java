import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sls.domain.Home;
import sls.domain.User;
import sls.domain.time.FixedTimeService;

import java.time.LocalDateTime;

public class HomeTests {

    @Test
    public void testHomeCanBeCreated() {
        // When: we create a home
        Home home = new Home(new FixedTimeService(LocalDateTime.now()));

        // Then: we can get the home address
        Assert.assertTrue(true );
    }


}
