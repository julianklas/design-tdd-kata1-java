package sls.domain;

import java.time.LocalDateTime;

public interface RequestToSkipSchedule {

    public boolean shouldSkipAction(LocalDateTime timeActionTakePlace) ;

}
