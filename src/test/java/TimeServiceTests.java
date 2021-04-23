import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sls.domain.time.FixedTimeService;

import java.time.LocalDateTime;

public class TimeServiceTests {

    @Test
    public void testFixedTimeServiceCanBeCreatedAndRetrieved() {
        // Given: a time service
        LocalDateTime someTime = LocalDateTime.of(2020,01,01,10,10,10);
        FixedTimeService timeService = new FixedTimeService(someTime);

        // Then: we have a user which can be greeted
        Assert.assertEquals(someTime, timeService.getCurrentTime());
    }


}
