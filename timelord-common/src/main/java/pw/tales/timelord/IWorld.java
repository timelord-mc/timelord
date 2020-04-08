package pw.tales.timelord;

public interface IWorld {
    void setTime(int tick);

    boolean shouldSync();

    int getSyncOffset();

    void disableDefaultTimeCycle();
}
