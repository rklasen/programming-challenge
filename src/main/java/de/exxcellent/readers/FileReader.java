package de.exxcellent.readers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class FileReader implements DataSource {
    protected List<String> content;

    public FileReader(String resourceURL) throws FileNotFoundException {
        accessRessource(resourceURL);
    }

    public Boolean accessRessource(String resourceURL) throws FileNotFoundException {
        try {
            content = Files.readAllLines(Paths.get(resourceURL));
            return true;
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }
}
