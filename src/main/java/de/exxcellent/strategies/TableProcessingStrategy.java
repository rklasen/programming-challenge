package de.exxcellent.strategies;

import de.exxcellent.data.TabularData;
import de.exxcellent.results.Result;

public interface TableProcessingStrategy {
    abstract Result process(TabularData data);
}
