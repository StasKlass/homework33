package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherMonitorTest  {

    private final WeatherMonitor weatherMonitor = new WeatherMonitor();

    static Stream<Arguments> provideTemperaturesForAverage() {
        return Stream.of(
                Arguments.of(List.of(10.0, 20.0, 30.0), 20.0),
                Arguments.of(List.of(-10.0, 0.0, 10.0), 0.0),
                Arguments.of(List.of(0.0, 0.0, 0.0), 0.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTemperaturesForAverage")
    @DisplayName("Test calculateAverageTemperature with valid inputs")
    void testCalculateAverageTemperature(List<Double> temperatures, double expected) {
        assertEquals(expected, weatherMonitor.calculateAverageTemperature(temperatures));
    }

    @Test
    @DisplayName("Test calculateAverageTemperature with empty list")
    public void testCalculateAverageTemperatureWithEmptyList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            weatherMonitor.calculateAverageTemperature(List.of());
        });
        assertEquals("Список температур не может быть нулевым или пустым.w", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 20.0, 30.0, false",
            "-10.0, 0.0, 10.0, true",
            "0.0, 0.0, 0.0, false"
    })
    @DisplayName("Test checkForFrostWarnings with various inputs")
    void testCheckForFrostWarnings(double temp1, double temp2, double temp3, boolean expected) {
        List<Double> temperatures = List.of(temp1, temp2, temp3);
        assertEquals(expected, weatherMonitor.checkForFrostWarnings(temperatures));
    }

    @Test
    @DisplayName("Test checkForFrostWarnings with empty list")
    public void testCheckForFrostWarningsWithEmptyList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            weatherMonitor.checkForFrostWarnings(List.of());
        });
        assertEquals("Список температур не может быть нулевым или пустым.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 8.0, 7.0, Low",
            "12.0, 15.0, 18.0, Medium",
            "20.0, 25.0, 30.0, High"
    })
    @DisplayName("Test evaluatePrecipitationLevels with various inputs")
    void testEvaluatePrecipitationLevels(double prec1, double prec2, double prec3, String expected) {
        List<Double> precipitationLevels = List.of(prec1, prec2, prec3);
        assertEquals(expected, weatherMonitor.evaluatePrecipitationLevels(precipitationLevels));
    }

    @Test
    @DisplayName("Test evaluatePrecipitationLevels with empty list")
    public void testEvaluatePrecipitationLevelsWithEmptyList() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            weatherMonitor.evaluatePrecipitationLevels(List.of());
        });
        assertEquals("Список осадков не может быть нулевым или пустым.", exception.getMessage());
    }
}
