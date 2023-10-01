package de.exxcellent.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import de.exxcellent.data.TabularData;
import de.exxcellent.exceptions.TableNotParsableException;
import de.exxcellent.results.Result;
import de.exxcellent.results.StringResult;

public class colDiffFinderStratTest {

    public static TabularData convertToTableData(String[][] testDataArray) {
        ArrayList<ArrayList<String>> testList = new ArrayList<>();

        for (String[] row : testDataArray) {
            testList.add(new ArrayList<>(Arrays.asList(row)));
        }

        TabularData tableData = new TabularData();
        tableData.data = Optional.of(testList);
        tableData.rows = testList.size();
        tableData.columns = testList.isEmpty() ? 0 : testList.get(0).size();

        return tableData;
    }

    @Test
    void strategyFindsCorrectColumnInts() throws Exception {
        DataProcessor processor = new DataProcessor(new columnWithMinimumDifferenceFinderStrategy(0, 1));

        String[][] testData = { { "1", "3" }, { "2", "5" }, { "3", "6" }, { "5", "6" } };
        var testTabular = convertToTableData(testData);

        Result result = processor.process(testTabular);

        // remember, this strategy finds the first entry of the row with the smallest
        // difference
        Result expectedResult = new StringResult("5");

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    void strategyFindsCorrectColumnDifferentInts() throws Exception {
        DataProcessor processor = new DataProcessor(new columnWithMinimumDifferenceFinderStrategy(0, 1));

        String[][] testData = { { "1", "2" }, { "2", "5" }, { "3", "6" }, { "5", "9" } };
        var testTabular = convertToTableData(testData);

        Result result = processor.process(testTabular);

        // remember, this strategy finds the first entry of the row with the smallest
        // difference
        Result expectedResult = new StringResult("1");

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    void strategyFindsCorrectColumnFloats() throws Exception {
        DataProcessor processor = new DataProcessor(new columnWithMinimumDifferenceFinderStrategy(0, 1));

        String[][] testData = { { "1.4", "2.0" }, { "2.5", "3.0" }, { "3.6", "4.0" }, { "4.7", "5.0" } };
        var testTabular = convertToTableData(testData);

        Result result = processor.process(testTabular);

        // remember, this strategy finds the first entry of the row with the smallest
        // difference
        Result expectedResult = new StringResult("4.7");

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    void strategyThrowsNotParsableForNan() throws Exception {
        assertThrows(TableNotParsableException.class, () -> {
            DataProcessor processor = new DataProcessor(new columnWithMinimumDifferenceFinderStrategy(0, 1));

            String[][] testData = { { "nan", "2.0" }, { "2.5", "3.0" } };
            var testTabular = convertToTableData(testData);

            processor.process(testTabular);
        });
    }

    @Test
    void strategyThrowsNotParsableForOutOfBounds() throws Exception {
        assertThrows(TableNotParsableException.class, () -> {
            DataProcessor processor = new DataProcessor(new columnWithMinimumDifferenceFinderStrategy(0, 2));

            String[][] testData = { { "1.0", "2.0" }, { "2.5", "3.0" } };
            var testTabular = convertToTableData(testData);

            processor.process(testTabular);
        });
    }

}
