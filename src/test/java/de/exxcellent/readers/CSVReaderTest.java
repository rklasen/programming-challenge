package de.exxcellent.readers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import de.exxcellent.data.TabularData;

public class CSVReaderTest {
    @Test
    void CSVReaderCanReadSimpleFile() throws Exception {
        CSVReader csvReader = new CSVReader("src/test/resources/de/exxcellent/challenge/simple.csv");
        TabularData data = csvReader.getData();
        assert (data.data.get().get(0).get(0).equals("super"));
        assert (data.data.get().get(0).get(1).equals("simple"));
    }

    @Test
    void CSVReaderThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> {
            new CSVReader("certainlyNotAFile.csv");
        });
    }
}
