package sls.domain.time;

import java.time.LocalDateTime;

public class FixedTimeService implements TimeService {

    public final LocalDateTime fixedTime;

    public FixedTimeService(LocalDateTime fixedTime) {
        this.fixedTime = fixedTime;
    }

    @Override
    public LocalDateTime getCurrentTime() {
        return this.fixedTime;
    }
}
