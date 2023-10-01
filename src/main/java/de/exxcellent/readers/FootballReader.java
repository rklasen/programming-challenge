package de.exxcellent.readers;

import java.io.FileNotFoundException;
import java.util.Optional;

import de.exxcellent.data.TabularData;
import de.exxcellent.exceptions.TableNotRectangularException;

public class FootballReader extends CSVReader {
    public FootballReader(String fileName) throws TableNotRectangularException, FileNotFoundException {
        super(fileName);
    }

    @Override
    public TabularData getData() {

        TabularData tabularData = super.getData();

        // first row is the header, so we remove it
        tabularData.rowHeader = Optional.of(tabularData.data.get().get(0));
        tabularData.data.get().remove(0);

        // convert the data to float
        // first count the number of rows and columns
        int rows = tabularData.data.get().size();
        int columns = tabularData.data.get().get(0).size();

        // from here on out, the data must be square. if not, we throw an exception
        for (int i = 0; i < rows; i++) {
            if (tabularData.data.get().get(i).size() != columns) {
                throw new TableNotRectangularException("Data is not rectangular.");
            }
        }

        tabularData.rows = rows;
        tabularData.columns = columns;

        return tabularData;
    }
}
