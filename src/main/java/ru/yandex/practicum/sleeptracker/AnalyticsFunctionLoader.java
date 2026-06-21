package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.util.List;
import java.util.function.Function;

public class AnalyticsFunctionLoader {
    private final List<Function<List<SleepingSession>, SleepAnalysisResult>> functions;

    public AnalyticsFunctionLoader() {
        //создаем список с доступными функциями
        functions = List.of(
                new AllSessionsCount(), //добавляем функцию расчета количества сессий сна
                new MinSleepSession(), //добавляем функцию расчета минимальной продолжительности
                new MaxSleepSession(), //добавляем функцию расчета максимальной продолжительности
                new AvgSleepSession(), //добавляем функцию расчета средней продолжительности
                new BadSessionsCount(), //добавляем функцию расчета количества сессий с плохим качеством сна
                new SleeplessSessionsCount(), //добавляем функцию расчета количества бессонных ночей
                new ChronotypeCalculator() //добавляем функцию расчета хронотипа
        );
    }

    public List<Function<List<SleepingSession>, SleepAnalysisResult>> getFunctions() {
        return functions;
    }
}


