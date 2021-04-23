package sls.domain.time;

import java.time.LocalDateTime;

public class ManuallyUpdatedTimeService implements TimeService {

    public LocalDateTime currrentTime;

    public ManuallyUpdatedTimeService(LocalDateTime currentTime) {
        this.currrentTime = currentTime;
    }

    @Override
    public LocalDateTime getCurrentTime() {
        return this.currrentTime;
    }

    public void setCurrentTime(LocalDateTime fixedTime) {
        this.currrentTime = fixedTime;
    }
}
