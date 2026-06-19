package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SleepingSessionLoader {

    public List<SleepingSession> getSleepingSessionListByFile(String filePath) {
        List<SleepingSession> sleepingSessions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

            sleepingSessions = reader.lines()
                    .map(line -> line.split(";"))
                    .map(value -> new SleepingSession(
                            LocalDateTime.parse(value[0], dtf),
                            LocalDateTime.parse(value[1], dtf),
                            SleepingQuality.valueOf(value[2])))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sleepingSessions;
    }

}
