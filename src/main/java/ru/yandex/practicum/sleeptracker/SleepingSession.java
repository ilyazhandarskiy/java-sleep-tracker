package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

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

    public Duration getDuration() {
        return duration;
    }

    public SleepingQuality getSleepingQuality() {
        return sleepingQuality;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
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
