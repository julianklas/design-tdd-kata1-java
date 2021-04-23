package sls.domain;

import sls.domain.schedule.NormalSchedule;
import sls.domain.schedule.PairHourOnSchedule;
import sls.domain.time.TimeService;

import java.time.LocalDateTime;
import java.util.*;

public class Home {

    private final List<RequestToSkipSchedule> userRequests = new ArrayList<>();

    private final Map<Light, LightSchedule> lightSchedules = new HashMap<>();

    private final TimeService timeService;

    public Home(TimeService timeService) {
        this.timeService = timeService;
    }

    public void addLight(Light aLight) {
        lightSchedules.put(aLight, new NormalSchedule(this.timeService));
    }

    public List<Light> getLights() {
        return new ArrayList( this.lightSchedules.keySet() );
    }

    public void update() {
        LocalDateTime newTime = timeService.getCurrentTime();

        boolean cancelActions = userRequests.stream().anyMatch( (request) -> request.shouldSkipAction(newTime) );

        if(cancelActions) return ;

        lightSchedules
                .forEach( (light, schedule) ->
                        schedule.applyOn( light )
                );
    }

    public void skipNormalSchedule(RequestToSkipSchedule request) {
        this.userRequests.add(request);
    }

    public void setSchedule(PairHourOnSchedule schedule, Light aLight) {
        this.lightSchedules.put(aLight, schedule);
    }

    public LightSchedule getScheduleFor(Light light) {
        return this.lightSchedules.get(light);
    }
}
