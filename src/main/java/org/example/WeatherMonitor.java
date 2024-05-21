package org.example;

import java.util.List;

public class WeatherMonitor {

    public double calculateAverageTemperature(List<Double> temperatures) {
        if (temperatures == null || temperatures.isEmpty()) {
            throw new IllegalArgumentException("Список температур не может быть нулевым или пустым.");
        }
        return temperatures.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public boolean checkForFrostWarnings(List<Double> temperatures) {
        if (temperatures == null || temperatures.isEmpty()) {
            throw new IllegalArgumentException("Список температур не может быть нулевым или пустым.");
        }
        return temperatures.stream().anyMatch(temp -> temp < 0);
    }

    public String evaluatePrecipitationLevels(List<Double> precipitationLevels) {
        if (precipitationLevels == null || precipitationLevels.isEmpty()) {
            throw new IllegalArgumentException("Список осадков не может быть нулевым или пустым.");
        }
        double average = precipitationLevels.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        if (average < 10) {
            return "Низкий";
        } else if (average < 20) {
            return "Середина";
        } else {
            return "Высокий";
        }
    }
}
