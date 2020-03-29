package framework.utilities.data.write;

import java.io.IOException;

/**
 * Describes a contract for writing data to a resource.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
@FunctionalInterface
interface IWrite {
    /**
     * @throws IOException IOException
     */
    void write() throws IOException;
}
