package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSleepSession implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "Максимальная продолжительность сессии (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        long i = sleepingSessions.stream()
                .map(SleepingSession::getDuration)
                .mapToLong(Duration::toMinutes)
                .max()
                .orElse(0);

        return new SleepAnalysisResult(DESCRIPTION, i);
    }
}
