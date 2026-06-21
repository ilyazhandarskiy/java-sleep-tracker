package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.BadSessionsCount;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("Тест функции BadSessionsCount")
class BadSessionsCountTest {

    public static final List<SleepingSession> sleepingSessionsEmpty = List.of();

    public static final List<SleepingSession> sleepingSessionsThree = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-22T21:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-21T21:21:21"), SleepingQuality.NORMAL)
    );

    private static final BadSessionsCount badSessionsCount = new BadSessionsCount();

    @DisplayName("Количество сессий с плохим качеством сна: 0")
    @Test
    public void shouldReturn0() {
        SleepAnalysisResult sleepAnalysisResult = badSessionsCount.apply(sleepingSessionsEmpty);
        Assertions.assertEquals("Количество сессий с плохим качеством сна: 0", sleepAnalysisResult.toString());
    }

    @DisplayName("Количество сессий с плохим качеством сна: 1")
    @Test
    public void shouldReturn1() {
        SleepAnalysisResult sleepAnalysisResult = badSessionsCount.apply(sleepingSessionsThree);
        Assertions.assertEquals("Количество сессий с плохим качеством сна: 1", sleepAnalysisResult.toString());
    }
}
