package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessSessionsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0);
        }

        //получаем дату и время начала первой сессии и конец второй сессии
        LocalDateTime startPeriod = sleepingSessions.getFirst().getStartTime();
        LocalDateTime endPeriod = sleepingSessions.getLast().getEndTime();

        //формируем даты начала и конца периода анализа
        LocalDate firstNightDate = startPeriod.toLocalDate();
        LocalDate lastNightDate = endPeriod.toLocalDate();

        //если первая сессия сна в файле началась после 12 дня, потенциальной ночью для сна
        // считается следующая ночь, а если до 12 — то предыдущая.
        if (startPeriod.toLocalTime().isAfter(LocalTime.of(12, 0))) {
            firstNightDate = firstNightDate.plusDays(1);

            //в случае если смещение начальной даты стало больше конечной, то приравниваем их
            if (firstNightDate.isAfter(lastNightDate)) {
                lastNightDate = firstNightDate;
            }
        }

        //считаем количество бессонных ночей
        long count = firstNightDate.datesUntil(lastNightDate.plusDays(1))
                .filter(localDate -> isSleepless(localDate, sleepingSessions))
                .count();

        return new SleepAnalysisResult(DESCRIPTION, count);
    }

    // метод для проверки бессонницы
    private boolean isSleepless(LocalDate date, List<SleepingSession> sleepingSessions) {
        LocalDateTime nightStart = date.atTime(0, 0); //нижняя граница
        LocalDateTime nightEnd = date.atTime(6, 0); //верхняя граница

        // проверка, на наличие сна в заданные пределы
        boolean result = sleepingSessions.stream()
                .anyMatch(sleepingSession ->
                        sleepingSession.getStartTime().isBefore(nightEnd)
                                && sleepingSession.getEndTime().isAfter(nightStart));

        //инверсируем - если был сон в заданные пределы, то бессонницы не было
        return !result;
    }
}
