package net.teamfruit.adhdplugin.simple;

public class SimpleSectionTimer {
    public final long sectionTime;
    public long lastActiveTime = Long.MIN_VALUE;

    public SimpleSectionTimer(long timeMs) {
        this.sectionTime = timeMs;
    }

    public boolean isResetNeededTime() {
        return sectionTime < System.currentTimeMillis() - this.lastActiveTime;
    }

    public void record() {
        this.lastActiveTime = System.currentTimeMillis();
    }
}
