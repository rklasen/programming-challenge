package de.exxcellent.readers;

import java.io.FileNotFoundException;

import de.exxcellent.challenge.UseCase;

public class DataSourceFactory {
    public DataSource createDataSource(UseCase useCase, String fileName) throws FileNotFoundException {
        if (useCase == UseCase.WEATHER) {
            return new WeatherReader(fileName);
        } else {
            throw new IllegalArgumentException("No Data Source available for use case: " + useCase);
        }
    }
}
