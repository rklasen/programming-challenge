package de.exxcellent.readers;

import de.exxcellent.data.TabularData;

public interface DataSource {
    public abstract Boolean accessRessource(String resourceURL) throws Exception;

    public abstract TabularData getData();
}
