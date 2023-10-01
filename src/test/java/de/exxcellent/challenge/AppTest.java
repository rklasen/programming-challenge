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

        // test if the result contains the expected string
        assert (result.contains(expected));
    }

    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }

}