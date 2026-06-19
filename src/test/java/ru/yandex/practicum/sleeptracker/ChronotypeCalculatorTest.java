package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.ChronotypeCalculator;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("Тест функции ChronotypeCalculator")
public class ChronotypeCalculatorTest {
    public static final List<SleepingSession> sleepingSessionsEmpty = List.of();

    public static final List<SleepingSession> sleepingSessionsOwl = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T23:21:21"), LocalDateTime.parse("2021-12-22T10:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-22T23:21:21"), LocalDateTime.parse("2021-12-23T13:21:21"), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.parse("2021-12-23T21:21:21"), LocalDateTime.parse("2021-12-24T06:21:21"), SleepingQuality.NORMAL)
    );

    public static final List<SleepingSession> sleepingSessionsLark = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T23:21:21"), LocalDateTime.parse("2021-12-22T10:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-22T21:21:21"), LocalDateTime.parse("2021-12-23T06:21:21"), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.parse("2021-12-23T21:21:21"), LocalDateTime.parse("2021-12-24T06:21:21"), SleepingQuality.NORMAL)
    );

    public static final List<SleepingSession> sleepingSessionsDove = List.of(
            new SleepingSession(LocalDateTime.parse("2021-12-21T21:21:21"), LocalDateTime.parse("2021-12-22T06:21:21"), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.parse("2021-12-22T23:21:21"), LocalDateTime.parse("2021-12-23T13:21:21"), SleepingQuality.GOOD)
    );

    ChronotypeCalculator calculator = new ChronotypeCalculator();

    @DisplayName("Хронотип пользователя: не определен - пустой список")
    @Test
    public void shouldReturn0() {
        SleepAnalysisResult sleepAnalysisResult = calculator.apply(sleepingSessionsEmpty);
        Assertions.assertEquals("Хронотип пользователя: не определен", sleepAnalysisResult.toString());
    }

    @DisplayName("Хронотип пользователя: Сова")
    @Test
    public void shouldReturnOwl() {
        SleepAnalysisResult sleepAnalysisResult = calculator.apply(sleepingSessionsOwl);
        Assertions.assertEquals("Хронотип пользователя: Сова", sleepAnalysisResult.toString());
    }

    @DisplayName("Хронотип пользователя: Жаворонок")
    @Test
    public void shouldReturnLark() {
        SleepAnalysisResult sleepAnalysisResult = calculator.apply(sleepingSessionsLark);
        Assertions.assertEquals("Хронотип пользователя: Жаворонок", sleepAnalysisResult.toString());
    }

    @DisplayName("Хронотип пользователя: Голубь")
    @Test
    public void shouldReturnDove() {
        SleepAnalysisResult sleepAnalysisResult = calculator.apply(sleepingSessionsDove);
        Assertions.assertEquals("Хронотип пользователя: Голубь", sleepAnalysisResult.toString());
    }

}
