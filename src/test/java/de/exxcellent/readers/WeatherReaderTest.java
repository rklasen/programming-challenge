package de.exxcellent.readers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import de.exxcellent.data.TabularData;
import de.exxcellent.exceptions.TableNotRectangularException;

public class WeatherReaderTest {

    @Test
    void WeatherReaderCanReadSimpleWeatherFileHeader() throws Exception {
        WeatherReader weatherReader = new WeatherReader("src/test/resources/de/exxcellent/challenge/simpleWeather.csv");
        TabularData weatherData = weatherReader.getData();

        assert (weatherData.rowHeader.get().get(0).equals("TMax"));
        assert (weatherData.rowHeader.get().get(1).equals("TMin"));
    }

    @Test
    void WeatherReaderCanReadSimpleWeatherFileData() throws Exception {
        WeatherReader weatherReader = new WeatherReader("src/test/resources/de/exxcellent/challenge/simpleWeather.csv");
        TabularData weatherData = weatherReader.getData();
        assert (weatherData.data.get().get(0).get(0).equals("90"));
        assert (weatherData.data.get().get(0).get(1).equals("70"));
    }

    @Test
    void WeatherReaderThrowsNotRectangularException() throws Exception {
        assertThrows(TableNotRectangularException.class, () -> {
            new WeatherReader("src/test/resources/de/exxcellent/challenge/nonRectangular.csv").getData();
        });
    }

}
