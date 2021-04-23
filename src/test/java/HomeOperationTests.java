import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sls.domain.time.FixedTimeService;
import sls.domain.Home;
import sls.domain.Light;
import sls.domain.User;
import sls.domain.time.ManuallyUpdatedTimeService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HomeOperationTests {

    @Test
    public void testHomeCanHaveLight() {
        // Given: a home with a light
        Home home = new Home(new FixedTimeService(LocalDateTime.now()));
        Light aLight = new Light();

        // When: we add the light to the home
        home.addLight(aLight);

        // Then: the home has 1 light
        Assert.assertEquals(1, home.getLights().size());
    }

    @Test
    public void testOneLightIsOffBeforeEight() {
        // Given: a home with a light
        LocalDateTime sevenAM = LocalDateTime.of(2021,4,30,7,8,0);
        Home home = new Home(new FixedTimeService(sevenAM));
        Light aLight = new Light();
        home.addLight(aLight);

        home.update();

        Light firstLight = home.getLights().stream().findFirst().get();

        // Then: the home has 1 light
        Assert.assertFalse(firstLight.isOn() );
    }

    @Test
    public void testManyLightAreOffBeforeEight() {
        // Given: a home with a light
        LocalDateTime sevenAM = LocalDateTime.of(2021,4,30,7,8,0);

        Home home = new Home(new FixedTimeService(sevenAM));
        Light aLight = new Light();
        Light anotherLight = new Light();

        home.addLight(aLight);
        home.addLight(anotherLight);

        home.update();

        Light firstLight = home.getLights().get(0);
        Light lastLight = home.getLights().get(1);

        // Then: all lights are off
        Assert.assertEquals(false, firstLight.isOn() );
        Assert.assertEquals(false, lastLight.isOn() );
        Assert.assertNotSame(firstLight, lastLight );
    }

    @Test
    public void testLightsAreOnAtNoon() {
        // Given: a home with a light
        Home home = new Home(new FixedTimeService(LocalDateTime.of(2021,4,30,12,0,0)));
        Light aLight = new Light();

        home.addLight(aLight);

        home.update();

        Light firstLight = home.getLights().get(0);

        // Then: all lights are off
        Assert.assertTrue(firstLight.isOn());
    }


    @Test
    public void testLightsAreOffAtEleven() {
        // Given: a home with a light
        LocalDateTime elevenPM = LocalDateTime.of(2021,4,30,23,8,0);

        Home home = new Home(new FixedTimeService(elevenPM));
        Light aLight = new Light();

        home.addLight(aLight);

        home.update();

        Light firstLight = home.getLights().get(0);

        // Then: all lights are off
        Assert.assertFalse( firstLight.isOn() );
    }



    @Test
    public void testUserCanCancelLightsOn() {
        // Given: a home with a light
        LocalDateTime now = LocalDateTime.of(2021, 4, 30, 12, 0, 0);
        Home home = new Home(new FixedTimeService(now));
        Light aLight = new Light();
        User john = new User("john", new FixedTimeService(now));

        john.cancelTodayScheduledActions(home);

        // When: ...
        home.addLight(aLight);

        home.update();

        Light theLight = home.getLights().get(0);

        // Then: the light hasn't been turned on
        Assert.assertFalse( theLight.isOn() );
    }

    @Test
    public void testUserActionsAreClearedTheDayAfter() {
        // Given: a home with a light
        FixedTimeService timeService = new FixedTimeService(LocalDateTime.of(2021, 4, 11, 12, 0, 0));
        Home home = new Home(timeService);
        User john = new User("john", timeService);

        // When: ...
        john.cancelTodayScheduledActions(home);

        home.update();

    }


    @Test
    public void testScheduleIsNormalDayAfterCancel() {
        // Given: a home with a light
        LocalDateTime now = LocalDateTime.of(2021, 4, 11, 12, 0, 0);
        ManuallyUpdatedTimeService timeService = new ManuallyUpdatedTimeService(now);
        Home home = new Home(timeService);
        Light aLight = new Light();
        User john = new User("john", new FixedTimeService(now));

        // When: ...
        john.cancelTodayScheduledActions(home);
        home.addLight(aLight);
        home.update();

        // Then: the light hasn't been turned on
        Light theLight = home.getLights().get(0);

        Assert.assertFalse( theLight.isOn() );

        // AndWhen: ...
        timeService.setCurrentTime(LocalDateTime.of(2021,4,12,12,0,0));
        home.update();

        // Then: the light hasn't been turned on
        Assert.assertTrue( theLight.isOn() );
    }




}
