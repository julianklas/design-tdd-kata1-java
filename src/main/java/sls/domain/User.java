package sls.domain;

import sls.domain.schedule.PairHourOnSchedule;
import sls.domain.time.TimeService;

public class User {

    private final String name;

    private final TimeService timeService;

    public User(String name, TimeService timeService) {
        this.name = name;
        this.timeService = timeService;
    }

    public String getName() {
        return this.name;
    }

    public void cancelTodayScheduledActions(Home home) {
        home.skipNormalSchedule( new RequestToSkipActionsUntilTomorrow(timeService) );
    }

}
