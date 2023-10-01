package de.exxcellent.results;

public class FloatResult implements Result {
    public Float value;

    public FloatResult(Float value) {
        this.value = value;
    }

    public String toString() {
        return this.value.toString();
    }
}
