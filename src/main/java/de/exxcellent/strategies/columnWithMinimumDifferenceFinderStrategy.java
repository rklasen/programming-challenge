package de.exxcellent.strategies;

import java.util.ArrayList;

import de.exxcellent.data.TabularData;
import de.exxcellent.exceptions.TableNotParsableException;
import de.exxcellent.results.Result;
import de.exxcellent.results.StringResult;

public class columnWithMinimumDifferenceFinderStrategy implements TableProcessingStrategy {

    private int colA, colB;

    public columnWithMinimumDifferenceFinderStrategy(int colA, int colB) {
        this.colA = colA;
        this.colB = colB;
    }

    @Override
    public Result process(TabularData tableData) {

        if (!tableData.data.isPresent()) {
            throw new RuntimeException("Float Data is not present!");
        }

        // we know that data is rectangular and we have the number of rows and columns
        int rows = tableData.rows;
        int columns = tableData.columns;

        // check if the columns we want to compare are actually present
        if (colA >= columns || colB >= columns) {
            throw new TableNotParsableException("Column index out of bounds");
        }

        // we will only attempt to parse the columns we want to compare
        // who knows what other columns might contain
        // we'll calculate the difference at the same time
        int minDiffRow = 0;
        Float diff = 0.0f;
        Float minDiff = Float.MAX_VALUE;
        Float colAval, colBval;

        for (int iRow = 0; iRow < rows; iRow++) {
            ArrayList<String> row = tableData.data.get().get(iRow);
            try {
                colAval = Float.parseFloat(row.get(colA));
                colBval = Float.parseFloat(row.get(colB));
            } catch (NumberFormatException e) {
                throw new TableNotParsableException("Could not parse column " + colA + " or " + colB);
            }

            diff = Math.abs(colAval - colBval);
            if (diff < minDiff) {
                minDiff = diff;
                minDiffRow = iRow;
            }
        }

        // the desired value is the first entry in minDiffRow
        return new StringResult(tableData.data.get().get(minDiffRow).get(0));
    }
}
