package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class SleepingSession {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final SleepingQuality sleepingQuality;
    private final Duration duration;


    public SleepingSession(LocalDateTime startTime, LocalDateTime endTime, SleepingQuality sleepingQuality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.sleepingQuality = sleepingQuality;
        this.duration = Duration.between(startTime, endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepingSession that = (SleepingSession) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && sleepingQuality == that.sleepingQuality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, sleepingQuality);
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", sleepQuality=" + sleepingQuality +
                '}';
    }
}
