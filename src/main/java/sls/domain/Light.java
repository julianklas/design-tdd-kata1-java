package sls.domain;

import java.util.Objects;
import java.util.UUID;

public class Light {

    public static final int MAX_POWER = 100;

    private final String id ;

    private boolean isOn;

    private boolean isBroken;

    private final LightLocations location;

    private int power;

    public Light() {
        this(UUID.randomUUID().toString(), LightLocations.INDOOR);
    }

    public Light(String id) {
        this(id, LightLocations.INDOOR);
    }

    public Light(LightLocations location) {
        this(UUID.randomUUID().toString(), location);
    }


    public Light(String id, LightLocations location) {
        this.id = id;
        this.isOn = false;
        this.isBroken = false;
        this.location = location;
    }

    public void turnOn() {
        turnOn(Light.MAX_POWER);
    }

    public void turnOn(int power) {
        if(isBroken()) return;
        isOn = true;
        setPower(power);
    }

    public Boolean isOn() {
        return this.isOn;
    }

    public void turnOff() {
        this.isOn = false;
        this.power = 0;
    }

    public boolean isBroken() {
        return this.isBroken;
    }

    public void setBroken() {
        this.isBroken = true;
        this.power = 0;
    }

    public LightLocations location() {
        return this.location;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int newPower) {
        if(!this.isOn) return;

        if(newPower > Light.MAX_POWER) {
            this.setBroken();
        }

        if(this.location == LightLocations.INDOOR)
            this.power = newPower % 10 == 0 ? newPower : newPower + 10 - (newPower % 10);
        else
            this.power = Light.MAX_POWER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Light light = (Light) o;
        return Objects.equals(id, light.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
