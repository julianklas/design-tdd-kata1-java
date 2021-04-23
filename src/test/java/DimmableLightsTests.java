import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sls.domain.Home;
import sls.domain.Light;
import sls.domain.LightLocations;

import java.time.LocalDateTime;

public class DimmableLightsTests {

    @Test
    public void testNewlyCreatedLightIsAtZeroPower() {
        Light aLight = new Light();
        Assert.assertEquals(0, aLight.getPower() );
    }

    @Test
    public void testLightsCanHaveFullPower() {
        Light aLight = new Light();
        aLight.turnOn(100);
        Assert.assertEquals(100, aLight.getPower() );
    }

    @Test
    public void testTurnOffLightsYieldsZeroPower() {
        Light aLight = new Light();
        aLight.turnOn();
        aLight.turnOff();
        Assert.assertEquals(0, aLight.getPower() );
    }

    @Test
    public void testPowerCanBeChanged() {
        Light aLight = new Light();
        aLight.turnOn();
        aLight.setPower(50);
        Assert.assertEquals(50, aLight.getPower() );
    }

    @Test
    public void testPowerCannotBeChangedForLightsOff() {
        Light aLight = new Light();
        aLight.turnOn();
        aLight.turnOff();
        aLight.setPower(50);
        Assert.assertEquals(0, aLight.getPower() );
    }

    @Test
    public void testOutdoorLightsAreNotDimmable() {
        Light aLight = new Light(LightLocations.OUTDOOR);
        aLight.turnOn(50);
        Assert.assertEquals(Light.MAX_POWER, aLight.getPower() );
    }

    @Test
    public void testPowerIsAllowedInIncrementsOfTenAndRoundedUp() {
        Light aLight = new Light(LightLocations.INDOOR);
        aLight.turnOn();

        aLight.setPower(55);
        Assert.assertEquals(60, aLight.getPower() );

        aLight.setPower(1);
        Assert.assertEquals(10, aLight.getPower() );

        aLight.setPower(99);
        Assert.assertEquals(100, aLight.getPower() );

        aLight.setPower(10);
        Assert.assertEquals(10, aLight.getPower() );
    }

    @Test
    public void testPowerBeyondMaxBrakesIndoorAndOutdoorLights() {
        Light indoorLight = new Light(LightLocations.INDOOR);
        indoorLight.turnOn(110);

        Light outdoorLight = new Light(LightLocations.OUTDOOR);
        outdoorLight.turnOn(110);

        Assert.assertTrue(indoorLight.isBroken());
        Assert.assertTrue(outdoorLight.isBroken());
    }


}
