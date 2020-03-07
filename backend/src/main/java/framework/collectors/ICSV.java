package framework.collectors;

import java.io.IOException;

/**
 * Extends its parent interface with specialized functionality related to CSV file extension
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface ICSV extends ICollector{
    void loadAndReadFile() throws IOException;
    void setDelimiter(String delimiter);
}
