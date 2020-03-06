package framework.collectors;

import java.io.IOException;

public interface ICSV extends ICollector{
    void loadAndReadFile() throws IOException;
    void setDelimiter(String delimiter);
}
