package de.exxcellent.challenge;

import java.io.FileNotFoundException;

import de.exxcellent.data.TabularData;
import de.exxcellent.exceptions.TableNotParsableException;
import de.exxcellent.exceptions.TableNotRectangularException;
import de.exxcellent.readers.DataSource;
import de.exxcellent.readers.DataSourceFactory;
import de.exxcellent.results.Result;
import de.exxcellent.strategies.DataProcessor;
import de.exxcellent.strategies.columnWithMinimumDifferenceFinderStrategy;

/**
 * The entry class for your solution. This class is only aimed as starting point
 * and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * 
     * @param args The CLI arguments passed
     */
    public String runWeatherTask() {
        try {
            DataSource weatherDataSource = new DataSourceFactory().createDataSource(UseCase.WEATHER,
                    "src/main/resources/de/exxcellent/challenge/weather.csv");

            TabularData weatherData = weatherDataSource.getData();
            DataProcessor weatherProcessor = new DataProcessor(new columnWithMinimumDifferenceFinderStrategy(1, 2));
            Result weatherResult = weatherProcessor.process(weatherData);
            return "Day of minimal Temperature Difference: " + weatherResult.toString();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (TableNotParsableException e) {
            System.out.println("CSV file is not parsable.");
        } catch (TableNotRectangularException e) {
            System.out.println("CSV file is not rectangular.");
        }
        return "There was an exception.";
    }

    public static void main(String... args) {
        App app = new App();
        System.out.println(app.runWeatherTask());
    }
}
