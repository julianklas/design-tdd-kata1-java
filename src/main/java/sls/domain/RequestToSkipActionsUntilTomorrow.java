package sls.domain;

import sls.domain.time.TimeService;

import java.time.LocalDateTime;

public class RequestToSkipActionsUntilTomorrow implements RequestToSkipSchedule {

    private final LocalDateTime tomorrowFirstMoment;
    private final LocalDateTime todayFirstMoment;


    public RequestToSkipActionsUntilTomorrow(TimeService timeService) {
        LocalDateTime now = timeService.getCurrentTime();

        LocalDateTime someMomentOfTomorrow = now.plusDays(1);

        this.todayFirstMoment = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),0,0,0);

        this.tomorrowFirstMoment = LocalDateTime.of(someMomentOfTomorrow.getYear(),
                someMomentOfTomorrow.getMonth(),
                someMomentOfTomorrow.getDayOfMonth(),
                0,0,0);
    }

    @Override
    public boolean shouldSkipAction(LocalDateTime timeActionTakePlace) {
        return timeActionTakePlace.isBefore(tomorrowFirstMoment) && timeActionTakePlace.isAfter(todayFirstMoment);
    }
}
