package pw.tales.timelord;

import java.time.LocalTime;

public class TimeLordCommon {
    public static final TimeLordCommon instance = new TimeLordCommon();

    static final int SECONDS_PER_HOUR = 3600;
    static final int SECONDS_OFFSET = SECONDS_PER_HOUR * 6;
    static final int SECONDS_PER_DAY = 86400;
    static final int TICKS_PER_DAY = 24000;

    public void worldTick(IWorld world) {
        if (world.shouldSync()) {
            int offset = world.getSyncOffset();
            int seconds = LocalTime.now().toSecondOfDay() + offset;

            int ticks = TICKS_PER_DAY * (seconds - SECONDS_OFFSET) / SECONDS_PER_DAY;

            world.disableDefaultTimeCycle();
            world.setTime(ticks);
        }
    }
}
