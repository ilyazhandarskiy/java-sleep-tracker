package ru.yandex.practicum.sleeptracker.functions;

public class SleepAnalysisResult {
    private final String description;
    private final Object value;

    public SleepAnalysisResult(String key, Object value) {
        this.description = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return description + ": " + value;
    }
}
