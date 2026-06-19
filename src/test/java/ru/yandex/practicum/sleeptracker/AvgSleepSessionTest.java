package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.AvgSleepSession;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("Тест функции AvgSleepSession")
public class AvgSleepSessionTest {

    public static final List<SleepingSession> sleepingSessionsEmpty = List.of();

    public static final List<SleepingSession> sleepingSessionsThree = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-22T21:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.NORMAL)
    );

    //аналитические функции
    public static final AvgSleepSession avgSleepSession = new AvgSleepSession();

    @DisplayName("Cредняя продолжительность сессии (в минутах): 0")
    @Test
    public void shouldReturn0() {
        SleepAnalysisResult sleepAnalysisResult = avgSleepSession.apply(sleepingSessionsEmpty);
        Assertions.assertEquals("Cредняя продолжительность сессии (в минутах): 0", sleepAnalysisResult.toString());
    }

    @DisplayName("Cредняя продолжительность сессии (в минутах): 480")
    @Test
    public void shouldReturn480() {
        SleepAnalysisResult sleepAnalysisResult = avgSleepSession.apply(sleepingSessionsThree);
        Assertions.assertEquals("Cредняя продолжительность сессии (в минутах): 480", sleepAnalysisResult.toString());
    }
}
