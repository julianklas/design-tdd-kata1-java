import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sls.domain.Home;
import sls.domain.Light;
import sls.domain.User;
import sls.domain.schedule.PairHourOnSchedule;
import sls.domain.time.FixedTimeService;
import sls.domain.time.ManuallyUpdatedTimeService;
import sls.domain.time.TimeService;

import java.time.LocalDateTime;

public class CustomSchedulerTests {

    @Test
    public void testHomeCanHaveSchedulesForLights() {
        // Given a user, a home, a light and a custom schedule
        Light aLight = new Light();
        Home home = new Home(new FixedTimeService(LocalDateTime.now()));

        TimeService timeService = new FixedTimeService(LocalDateTime.of(2021,01,01,10,00,00));

        // When: we set the schedule
        PairHourOnSchedule onOnPairHours = new PairHourOnSchedule(timeService);
        home.addLight(aLight);
        home.setSchedule(onOnPairHours, aLight);

        // Then: it has a schedule
        Assert.assertNotNull(home.getScheduleFor(aLight));
    }

    @Test
    public void test() {
       // Given a user, a home, a light and a custom schedule
       LocalDateTime tenAM = LocalDateTime.of(2020,10,10,10,0,0);
       LocalDateTime elevenAM = LocalDateTime.of(2020,10,10,11,0,0);
       ManuallyUpdatedTimeService timeService = new ManuallyUpdatedTimeService(tenAM);

       Light aLight = new Light();
       PairHourOnSchedule onOnPairHours = new PairHourOnSchedule(timeService);
       Home home = new Home(timeService);
       home.addLight(aLight);

       // When: the schedule is set
       home.setSchedule(onOnPairHours, aLight);

       // When: we add a light and update the state
       home.update();

       // Then: the light is on because it's a pair hour number
       Assert.assertTrue(aLight.isOn());

       timeService.setCurrentTime(elevenAM);

       // When: we add a light and update the state
       home.update();

       // Then: the light is on because it's an odd hour number
       Assert.assertFalse(aLight.isOn());
    }


}
