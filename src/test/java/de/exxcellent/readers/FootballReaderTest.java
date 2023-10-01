package de.exxcellent.readers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import de.exxcellent.data.TabularData;
import de.exxcellent.exceptions.TableNotRectangularException;

public class FootballReaderTest {
    @Test
    void FootballReaderCanReadSimpleWeatherFileHeader() throws Exception {
        FootballReader fr = new FootballReader(
                "src/test/resources/de/exxcellent/challenge/simpleWeather.csv");
        TabularData fd = fr.getData();

        assert (fd.rowHeader.get().get(0).equals("TMax"));
        assert (fd.rowHeader.get().get(1).equals("TMin"));
    }

    @Test
    void FootballReaderCanReadSimpleWeatherFileData() throws Exception {
        FootballReader fr = new FootballReader(
                "src/test/resources/de/exxcellent/challenge/simpleWeather.csv");
        TabularData fd = fr.getData();
        assert (fd.data.get().get(0).get(0).equals("90"));
        assert (fd.data.get().get(0).get(1).equals("70"));
    }

    @Test
    void FootballReaderThrowsNotRectangularException() throws Exception {
        assertThrows(TableNotRectangularException.class, () -> {
            new FootballReader("src/test/resources/de/exxcellent/challenge/nonRectangular.csv").getData();
        });
    }

}
