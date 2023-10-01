package de.exxcellent.readers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.UseCase;

public class DataSourceFactoryTest {

    @Test
    void dataSourceFactoryCreatesWeatherReader() throws Exception {
        DataSourceFactory dsf = new DataSourceFactory();
        DataSource ds = dsf.createDataSource(UseCase.WEATHER,
                "src/test/resources/de/exxcellent/challenge/simple.csv");

        assert (ds instanceof WeatherReader);
    }

    @Test
    void dataSourceThrowsExceptionForNonexistentUseCase() {
        DataSourceFactory dsf = new DataSourceFactory();

        assertThrows(IllegalArgumentException.class, () -> {
            dsf.createDataSource(UseCase.NOTEXISTING,
                    "src/test/resources/de/exxcellent/challenge/simple.csv");
        });
    }

    @Test
    void dataSouceFactoryThrowsFileNotFoundException() {
        DataSourceFactory dsf = new DataSourceFactory();

        assertThrows(FileNotFoundException.class, () -> {
            dsf.createDataSource(UseCase.WEATHER,
                    "certainlyNotAFile.csv");
        });
    }

}
