package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronotypeCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "Хронотип пользователя";

    private static final LocalTime OWL_START = LocalTime.of(23, 0);
    private static final LocalTime OWL_END = LocalTime.of(9, 0);
    private static final LocalTime LARK_START = LocalTime.of(22, 0);
    private static final LocalTime LARK_END = LocalTime.of(7, 0);

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, "не определен");
        }

        return new SleepAnalysisResult(DESCRIPTION, findChronoType(sleepingSessions));
    }

    private Chronotype findChronoType(List<SleepingSession> sessionList) {
        // формируем только ночные сессии
        List<SleepingSession> nightSessions = sessionList.stream()
                .filter(this::isNightSession)
                .toList();

        long owl = nightSessions.stream().filter(s -> classify(s) == Chronotype.OWL).count();
        long lark = nightSessions.stream().filter(s -> classify(s) == Chronotype.LARK).count();
        long pigeon = nightSessions.stream().filter(s -> classify(s) == Chronotype.DOVE).count();

        return getChronotype(owl, lark, pigeon);
    }

    // вычисляем хронотип по значениям
    private static Chronotype getChronotype(long owl, long lark, long pigeon) {
        if (owl > lark && owl > pigeon) {
            return Chronotype.OWL;
        } else if (lark > owl && lark > pigeon) {
            return Chronotype.LARK;
        } else {
            return Chronotype.DOVE;
        }
    }

    private boolean isNightSession(SleepingSession s) {
        LocalDateTime start = s.getStartTime();
        LocalDateTime end = s.getEndTime();

        // Сессия пересекает ночь, если начало < 06:00 и конец > 00:00 любого дня
        return start.toLocalDate().datesUntil(end.toLocalDate().plusDays(1)).anyMatch(date -> {
            LocalDateTime nightStart = date.atStartOfDay();
            LocalDateTime nightEnd = date.atTime(LocalTime.of(6, 0));
            return start.isBefore(nightEnd) && end.isAfter(nightStart);
        });
    }

    private Chronotype classify(SleepingSession session) {
        LocalTime startTime = session.getStartTime().toLocalTime();
        LocalTime endTime = session.getEndTime().toLocalTime();

        // Сова: засыпание после 23:00 И пробуждение после 9:00
        if (startTime.isAfter(OWL_START) && endTime.isAfter(OWL_END)) {
            return Chronotype.OWL;
        }
        // Жаворонок: засыпание до 22:00 И пробуждение до 7:00
        if (startTime.isBefore(LARK_START) && endTime.isBefore(LARK_END)) {
            return Chronotype.LARK;
        }
        // Все остальные случаи — Голубь
        return Chronotype.DOVE;
    }
}
