package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.SessionsSum;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final List<SleepingSession> sleepingSessions = new ArrayList<>();
    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Укажите путь к файлу с логом сна в аргументах запуска.");
            return;
        }

        String filePath = args[0];

        //формируем список сессий сна по файлу
        createSleepingSessionListByFile(filePath);

        createAnalyticsSessionList();

        functions.forEach(function -> {
            System.out.println(function.apply(sleepingSessions));
        });

    }

    private static void createSleepingSessionListByFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

            reader.lines().toList().stream().map(line -> line.split(";")).forEach(line -> {
                SleepingSession ss = new SleepingSession(LocalDateTime.parse(line[0], dtf),
                        LocalDateTime.parse(line[1], dtf), SleepingQuality.valueOf(line[2]));
                sleepingSessions.add(ss);
            });

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createAnalyticsSessionList() {
        functions.add(new SessionsSum());
    }
}