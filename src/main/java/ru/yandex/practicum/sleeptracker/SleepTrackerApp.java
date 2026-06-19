package ru.yandex.practicum.sleeptracker;

import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {
    private static final SleepingSessionLoader sleepingSessionLoader = new SleepingSessionLoader();
    private static final AnalyticsFunctionLoader analyticsFunctionLoader = new AnalyticsFunctionLoader();

    // в параметры запуска под 0 индекс добавляем "src/main/resources/sleep_log.txt"
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу с логом сна в аргументах запуска.");
            return;
        }
        // присваиваем значение из аргументов запуска
        String filePath = args[0];

        // создаем список сессий
        List<SleepingSession> sleepingSessions =
                new ArrayList<>(sleepingSessionLoader.getSleepingSessionListByFile(filePath));

        // запускаем расчет аналитических функций
        analyticsFunctionLoader.getFunctions().stream()
                .map(function -> function.apply(sleepingSessions))
                .forEach(System.out::println);
    }
}