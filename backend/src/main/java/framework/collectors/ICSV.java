package framework.collectors;

import java.io.IOException;

/**
 * Extends its parent interface with specialized functionality related to CSV file extension
 */
public interface ICSV extends ICollector{
    void loadAndReadFile() throws IOException;
    void setDelimiter(String delimiter);
}
