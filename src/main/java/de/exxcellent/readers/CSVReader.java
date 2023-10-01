package de.exxcellent.readers;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import de.exxcellent.data.TabularData;

public class CSVReader extends FileReader {

    public CSVReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    public TabularData getData() {

        TabularData result = new TabularData();

        ArrayList<ArrayList<String>> tabularData = new ArrayList<>();

        // super class is FileReader, so we have super.content
        super.content.forEach(line -> {
            ArrayList<String> row = new ArrayList<>();
            String[] lineArray = line.split(",");
            for (String s : lineArray) {
                row.add(s.trim());
            }
            tabularData.add(row);
        });
        result.data = java.util.Optional.of(tabularData);
        return result;
    }
}
