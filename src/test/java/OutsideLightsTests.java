import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sls.domain.Home;
import sls.domain.Light;
import sls.domain.LightLocations;
import sls.domain.time.FixedTimeService;

import java.time.LocalDateTime;

public class OutsideLightsTests {

    @Test
    public void testDefaultLocationForLightIsIndoor() {
        // Given: a home with an outside light
        Home home = new Home(new FixedTimeService(LocalDateTime.now()));
        Light aLight = new Light();

        // When: we add the light to the home
        home.addLight(aLight);

        Light retrievedLight = home.getLights().get(0);

        // Then: the home has 1 light
        Assert.assertEquals( LightLocations.INDOOR, retrievedLight.location() );
    }

    @Test
    public void testOutsideBulbsCanBeCreated() {
        // Given: a home with an outside light
        Home home = new Home(new FixedTimeService(LocalDateTime.now()));
        Light aLight = new Light(LightLocations.OUTDOOR);

        // When: we add the light to the home
        home.addLight(aLight);

        Light retrievedLight = home.getLights().get(0);

        // Then: the home has 1 light
        Assert.assertEquals( LightLocations.OUTDOOR, retrievedLight.location() );
    }


    @Test
    public void testOutsideLightsOpposeToIndoorWhenTheyShouldBeOn() {
        // Given: a home with an outside light
        LocalDateTime sixAM = LocalDateTime.of(2020, 04, 30, 6, 0, 0);

        Home home = new Home(new FixedTimeService(sixAM));
        Light indoor = new Light(LightLocations.INDOOR);
        Light outdoor = new Light(LightLocations.OUTDOOR);

        // When:
        home.addLight(indoor);
        home.addLight(outdoor);
        home.update();

        Light indoorLight = home.getLights().stream().filter((l) -> l.location() == LightLocations.INDOOR ).findFirst().get();
        Light outdoorLight = home.getLights().stream().filter((l) -> l.location() == LightLocations.OUTDOOR ).findFirst().get();

        // Then:
        Assert.assertEquals(2,home.getLights().size());
        Assert.assertNotSame( indoorLight, outdoorLight);

        Assert.assertFalse( indoorLight.isOn() );
        Assert.assertTrue( outdoorLight.isOn() );

    }

}
