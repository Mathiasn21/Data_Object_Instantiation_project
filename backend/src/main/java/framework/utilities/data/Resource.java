package framework.utilities.data;

import framework.utilities.data.read.IRead;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

/** Class representing a data source
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class Resource {
    private final IRead readData;

    /**
     * @param readData {@link IRead}
     */
    public Resource(IRead readData) { this.readData = readData; }

    /**
     * @return {@link BufferedReader}
     * @throws FileNotFoundException FileNotFoundException
     */
    public final @NotNull BufferedReader getData() throws FileNotFoundException {
        return readData.read();
    }
}
