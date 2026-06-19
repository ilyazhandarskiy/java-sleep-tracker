package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.MinSleepSession;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("Тест функции MinSleepSession")
public class MinSleepSessionTest {
    public static final List<SleepingSession> sleepingSessionsEmpty = List.of();

    public static final List<SleepingSession> sleepingSessionsThree = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-22T21:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-20T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.parse("2021-12-10T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.NORMAL)
    );

    //аналитические функции
    public static final MinSleepSession minSleepSession = new MinSleepSession();

    @DisplayName("Минимальная продолжительность сессии (в минутах): 0")
    @Test
    public void shouldReturn0() {
        SleepAnalysisResult sleepAnalysisResult = minSleepSession.apply(sleepingSessionsEmpty);
        Assertions.assertEquals("Минимальная продолжительность сессии (в минутах): 0", sleepAnalysisResult.toString());
    }

    @DisplayName("Максимальная продолжительность сессии (в минутах): 1440")
    @Test
    public void shouldReturn1440() {
        SleepAnalysisResult sleepAnalysisResult = minSleepSession.apply(sleepingSessionsThree);
        Assertions.assertEquals("Минимальная продолжительность сессии (в минутах): 1440", sleepAnalysisResult.toString());
    }
}
