package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.*;
import ru.yandex.practicum.sleeptracker.functions.AllSessionsCount;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("Тест функции SessionsCount")
public class AllSessionsCountTest {

    public static final List<SleepingSession> sleepingSessionsEmpty = List.of();

    public static final List<SleepingSession> sleepingSessionsThree = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-22T21:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.NORMAL)
    );

    //аналитические функции
    public static final AllSessionsCount allSessionsCount = new AllSessionsCount();

    @DisplayName("Количество сессий сна: 0")
    @Test
    public void shouldReturn0() {
        SleepAnalysisResult sleepAnalysisResult = allSessionsCount.apply(sleepingSessionsEmpty);
        Assertions.assertEquals("Количество сессий сна: 0", sleepAnalysisResult.toString());
    }

    @DisplayName("Количество сессий сна: 3")
    @Test
    public void shouldReturn3() {
        SleepAnalysisResult sleepAnalysisResult = allSessionsCount.apply(sleepingSessionsThree);
        Assertions.assertEquals("Количество сессий сна: 3", sleepAnalysisResult.toString());
    }
}


