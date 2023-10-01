# Romans Solution

The main class uses all of the above to get print a result. By default, it will read the weather data and use the `columnWithMinimumDifferenceFinderStrategy` to find the day with the smallest temperature spread.

If the flag `--football` is passed, it will read the football data and use the same strategy to find the team with the smallest goal difference. The Readers will take care of the different data formats.

```mermaid
flowchart TD
Main -- with --- UseCase
UseCase -- needed for --> DataSourceFactory
Main -- chooses filename for --> DataSourceFactory
Main -- creates --> DataProcessor
Main -- assigns --> DataProcessingStrategy
DataSourceFactory -- fabricates --> DataSource
DataSource -- outputs --> Data
Data -- goes into --> DataProcessor
DataProcessingStrategy -- to --> DataProcessor
DataProcessor -- calculates --> Result
```

## Use Cases

The app should handle two `UseCase`s, one for weather and one for football.

```mermaid
classDiagram

class UseCase{
    <<enumeration>>
    +WEATHER
    +FOOTBALL
}
```

## Data Sources

The data sources are CSV files. In the future, they may be JSON or XML or something else. They may also come from somewhere other than files, like a database or a network resource.

We'll use a factory pattern to create the correct `DataSource` for the given `UseCase`.

```mermaid
classDiagram

    class DataSource  {
        <<interface>>
          +accessResource(resourceURL: String): Boolean
          +getData(): TabularData
    }

    class DataSourceFactory {
        +createDataSource(application: UseCase, resourceURL: String): DataSource
    }

    class FileReader  {
        -readFile(fileURL: String): Boolean
        +accessResource(resourceURL: String): Boolean
        +getData(): TabularData
    }

    class CSVReader {
        +getData(): TabularData
    }

    class WeatherReader {
        +getData(): TabularData
    }

    class FootballReader {
        +getData(): TabularData
    }

    DataSourceFactory --> DataSource : creates implementation of
    WeatherReader --> CSVReader : extends
    FootballReader --> CSVReader : extends
    CSVReader  --> FileReader : extends
    FileReader --> DataSource : implements

```

## Tabular Data

The `Data` itself is a little troublesome. Since it comes from a CSV file, it's tabular data. But:

- we don't know if the first row is a header or not (but probably is)
- we don't know if the first column is a header or not (but probably isn't)
- we don't know if the data is numeric or date-like or string (or worse: a mixture!)
- we don't know if the data is rectangular or not

So for the `TabularData` class, we'll have optional row and column headers (String arrays). The table data must be rectangular and of type string.

```mermaid
classDiagram
    class TabularData {
        + rowHeader: Optional[String[]]
        + colHeader: Optional[String[]]
        + rows: int
        + cols: int
        + data: String[][]
    }
```

The use-case specific DataReaders are responsible for parsing the data from the `DataSource` into the `TabularData` object. They should throw an exception if the data is not rectangular or somehow corrupt.

## Data Processing

To get what we want from the data, we'll use the strategy pattern. The strategy is also responsible for casting the data to the correct type (a different strategy may for example compare dates or strings)

```mermaid
classDiagram
    class DataProcessingStrategy {
    <<interface>>
        +process(data: Data): Optional[Result]
    }

    class columnWithMinimumDifferenceFinderStrategy {
        -colA, colB: int
        +ColumnMaximumFinder(colA, colB: int)
        +process(data: Data): Optional[String]
    }

    class DataProcessor {
        -strategy: DataProcessingStrategy
        +DataProcessor(strategy: DataProcessingStrategy)
        +analyzeData(data: Data): Optional[Result]
    }

    DataProcessingStrategy <-- columnWithMinimumDifferenceFinderStrategy : implements
    DataProcessor --> DataProcessingStrategy : uses
```

Choosing which `Result` type to return is the task of the `Strategy`. If the `Strategy` returns an empty `Optional`, the `DataProcessor` will return an empty `Optional` as well. If the `Strategy` can not correctly parse some table data (e.g. if the data is not numeric), it will throw an exception.

## Results

A `Result` can be either a String (if we're looking for a name in a row or col header for example) or a number or tuple or anything.

Specific Results must be subclasses of `Result`.

```mermaid
classDiagram
class Result{
    <<interface>>
    +abstract String toString()
}

class StringResult{
    +String toString()
}

class intResult{
    +String toString()
}

class floatResult{
    +String toString()
}

intResult --> Result: implements
floatResult --> Result: implements
StringResult --> Result: implements
```

## Exceptions

For the sake of completeness, we'll also have a `TableNotRectangularException` and a `TableNotParsableException` which may be thrown by the `FileReader` (which ensures the data is rectangular) and the Weather/Football Readers, which need to parse String values to floats.
