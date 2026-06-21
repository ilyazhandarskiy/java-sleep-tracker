package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.SleeplessSessionsCount;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("Тест функции SleeplessSessionsCount")
public class SleeplessSessionsCountTest {

    public static final List<SleepingSession> sleepingSessionsEmpty = List.of();

    public static final List<SleepingSession> sleepingSessionAfter12NotMatch = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T12:21:21"), LocalDateTime.parse("2021-12-21T23:21:21"), SleepingQuality.BAD)
    );

    public static final List<SleepingSession> sleepingSessionsBefore12InMatch = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T03:21:21"), LocalDateTime.parse("2021-12-21T23:21:21"), SleepingQuality.BAD)
    );

    public static final List<SleepingSession> sleepingSessionsWith3SleeplessDays = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T11:21:21"), LocalDateTime.parse("2021-12-21T23:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-22T12:21:21"), LocalDateTime.parse("2021-12-22T23:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-23T12:21:21"), LocalDateTime.parse("2021-12-23T23:21:21"), SleepingQuality.BAD)
    );

    public static final List<SleepingSession> sleepingSessionsFor3DaysWithoutSleeplessDays = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T12:21:21"), LocalDateTime.parse("2021-12-24T23:21:21"), SleepingQuality.BAD)
    );


    private static final SleeplessSessionsCount sleeplessSessionsCount = new SleeplessSessionsCount();

    @DisplayName("Количество бессонных ночей: 0 - пустой список")
    @Test
    public void shouldReturn0() {
        SleepAnalysisResult sleepAnalysisResult = sleeplessSessionsCount.apply(sleepingSessionsEmpty);
        Assertions.assertEquals("Количество бессонных ночей: 0", sleepAnalysisResult.toString());
    }

    @DisplayName("Количество бессонных ночей: 1 - начало после 12:00")
    @Test
    public void shouldReturn1ForStartAfter12() {
        SleepAnalysisResult sleepAnalysisResult = sleeplessSessionsCount.apply(sleepingSessionAfter12NotMatch);
        Assertions.assertEquals("Количество бессонных ночей: 1", sleepAnalysisResult.toString());
    }

    @DisplayName("Количество бессонных ночей: 0 - начало до 12:00")
    @Test
    public void shouldReturn0ForStartBefore12() {
        SleepAnalysisResult sleepAnalysisResult = sleeplessSessionsCount.apply(sleepingSessionsBefore12InMatch);
        Assertions.assertEquals("Количество бессонных ночей: 0", sleepAnalysisResult.toString());
    }

    @DisplayName("Количество бессонных ночей: 3")
    @Test
    public void shouldReturn3() {
        SleepAnalysisResult sleepAnalysisResult = sleeplessSessionsCount.apply(sleepingSessionsWith3SleeplessDays);
        Assertions.assertEquals("Количество бессонных ночей: 3", sleepAnalysisResult.toString());
    }

    @DisplayName("Количество бессонных ночей: 0 - 3 дня сна")
    @Test
    public void shouldReturn0For3DaysSession() {
        SleepAnalysisResult sleepAnalysisResult = sleeplessSessionsCount.apply(sleepingSessionsFor3DaysWithoutSleeplessDays);
        Assertions.assertEquals("Количество бессонных ночей: 0", sleepAnalysisResult.toString());
    }
}
