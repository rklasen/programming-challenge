package de.exxcellent.strategies;

import de.exxcellent.data.TabularData;
import de.exxcellent.results.Result;

public class DataProcessor {
    private TableProcessingStrategy strategy;

    public DataProcessor(TableProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(TableProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    public Result process(TabularData data) {
        return strategy.process(data);
    }
}
