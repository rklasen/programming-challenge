package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Example JUnit 5 test case.
 * 
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void testAppWeather() {
        App app = new App();
        String result = app.runWeatherTask();
        String expected = "Day of minimal Temperature Difference:";

        assert (result.contains(expected));
    }

    @Test
    void runFootball() {

        App app = new App();
        String result = app.runFootballTask();
        String expected = "Best football Team:";

        assert (result.contains(expected));

    }

    @Test
    void appParsesArgumentCorreclty() {
        App.main("--football");
    }

}