package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AvgSleepSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "Cредняя продолжительность сессии (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long i = (long) sleepingSessions.stream()
                .map(SleepingSession::getDuration)
                .mapToLong(Duration::toMinutes)
                .average()
                .orElse(0);

        return new SleepAnalysisResult(DESCRIPTION, i);
    }
}
