package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.MaxSleepSession;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("Тест функции MaxSleepSession")
public class MaxSleepSessionTest {
    public static final List<SleepingSession> sleepingSessionsEmpty = List.of();

    public static final List<SleepingSession> sleepingSessionsThree = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-22T21:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.NORMAL)
    );

    //аналитические функции
    public static final MaxSleepSession maxSleepSession = new MaxSleepSession();

    @DisplayName("Максимальная продолжительность сессии (в минутах): 0")
    @Test
    public void shouldReturn0() {
        SleepAnalysisResult sleepAnalysisResult = maxSleepSession.apply(sleepingSessionsEmpty);
        Assertions.assertEquals("Максимальная продолжительность сессии (в минутах): 0", sleepAnalysisResult.toString());
    }

    @DisplayName("Максимальная продолжительность сессии (в минутах): 1440")
    @Test
    public void shouldReturn1440() {
        SleepAnalysisResult sleepAnalysisResult = maxSleepSession.apply(sleepingSessionsThree);
        Assertions.assertEquals("Максимальная продолжительность сессии (в минутах): 1440", sleepAnalysisResult.toString());
    }
}
