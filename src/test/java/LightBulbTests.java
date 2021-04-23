
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import sls.domain.Light;

public class LightBulbTests {

    @Test
    public void testLightsAreOffWhenCreated() {
        // Given: a light
        Light aLight = new Light();

        // Then:
        Assert.assertFalse(aLight.isOn() );
    }


    @Test
    public void testLightsAreNotBrokenWhenCreated() {
        // Given: a light
        Light aLight = new Light();

        // Then: it is not broken
        Assert.assertFalse(aLight.isBroken() );
    }

    @Test
    public void testLightCanBeTurnedOn() {
        // Given: a light
        Light aLight = new Light();

        // When: we turn on the light
        aLight.turnOn();

        // Then:
        Assert.assertTrue(aLight.isOn() );
    }

    @Test
    public void testLightCanBeTurnedOff() {
        // Given: a light
        Light aLight = new Light();

        // When: we turn on the light
        aLight.turnOff();

        // Then:
        Assert.assertFalse(aLight.isOn() );
    }

    @Test
    public void testLightCanBeBroken() {
        // Given: a light
        Light aLight = new Light();

        // When: light is broken
        aLight.setBroken();

        // Then:
        Assert.assertTrue(aLight.isBroken());
    }

    @Test
    public void testBrokenLightCannotBeTurnedOn() {
        // Given: a light
        Light aLight = new Light();

        // When: light is broken
        aLight.setBroken();
        aLight.turnOn();

        // Then:
        Assert.assertFalse(aLight.isOn());
    }

}
