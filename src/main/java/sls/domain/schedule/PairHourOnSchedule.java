package sls.domain.schedule;

import sls.domain.Light;
import sls.domain.LightLocations;
import sls.domain.LightSchedule;
import sls.domain.time.TimeService;

public class PairHourOnSchedule implements LightSchedule {

    private final TimeService timeService;

    public PairHourOnSchedule(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public void applyOn(Light light) {
        boolean shouldTurnOnAccordingToNormalSchedule = new NormalSchedule(timeService).shouldTurnOn(light);
        boolean isOn = light.isOn();
        int hour = timeService.getCurrentTime().getHour();
        boolean pairHour = hour % 2 == 0;

        // These 'if' are horrible
        if(!pairHour && isOn) {
            light.turnOff();
            return;
        }

        if(!pairHour && !isOn) {
            return;
        }

        if(pairHour && isOn) {
            return;
        }

        if(pairHour && !isOn) {
            light.turnOn();
            return;
        }

    }
}
