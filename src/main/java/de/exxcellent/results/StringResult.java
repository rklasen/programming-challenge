package de.exxcellent.results;

public class StringResult implements Result {
    public String value;

    public StringResult(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
