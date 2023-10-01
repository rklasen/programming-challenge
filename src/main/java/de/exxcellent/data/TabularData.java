package de.exxcellent.data;

import java.util.ArrayList;
import java.util.Optional;

public class TabularData {
    public Optional<ArrayList<String>> rowHeader;
    public Optional<ArrayList<String>> columnHeader;
    public Optional<ArrayList<ArrayList<String>>> data;
    public int rows = 0;
    public int columns = 0;

    public TabularData() {
        this.rowHeader = Optional.empty();
        this.columnHeader = Optional.empty();
        this.data = Optional.empty();
    }

    public String toString() {

        String result = "";
        if (this.rowHeader.isPresent()) {
            result += "Row header: " + this.rowHeader.get() + "\n";
        }
        if (this.columnHeader.isPresent()) {
            result += "Column header: " + this.columnHeader.get() + "\n";
        }
        if (this.data.isPresent()) {
            result += "Data: " + this.data.get() + "\n";
        }
        return result;
    }
}
