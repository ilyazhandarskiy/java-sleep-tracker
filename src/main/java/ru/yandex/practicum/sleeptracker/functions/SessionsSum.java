package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class SessionsSum implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult("Количество сессий сна", sleepingSessions.size());
    }
}
