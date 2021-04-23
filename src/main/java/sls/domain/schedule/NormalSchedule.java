package sls.domain.schedule;

import sls.domain.Light;
import sls.domain.LightLocations;
import sls.domain.LightSchedule;
import sls.domain.time.TimeService;

public class NormalSchedule implements LightSchedule {

    private final TimeService timeService;

    public NormalSchedule(TimeService timeService) {
        this.timeService = timeService;
    }

    public boolean shouldTurnOn(Light light) {
        int hour = timeService.getCurrentTime().getHour();

        LightLocations locationToTurnOn = (hour >= 8 && hour < 23 ) ? LightLocations.INDOOR : LightLocations.OUTDOOR;

        if(locationToTurnOn == light.location() && !light.isOn() && !light.isBroken())
            return true;
        else
            return false;
    }

    @Override
    public void applyOn(Light light) {
        boolean shouldTurnOn = shouldTurnOn(light);

        if(shouldTurnOn) light.turnOn();
        else light.turnOff();
    }
}
