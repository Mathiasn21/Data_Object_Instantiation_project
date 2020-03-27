package framework.utilities.data.read;

import java.io.BufferedReader;
import java.io.IOException;

/** Interface describing a contract for reading with a String resource
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.0.0
 */
public interface IRead {

    /**
     * @throws IOException e
     * @return {@link BufferedReader}
     */
    BufferedReader read() throws IOException;

    /**
     * @return String
     */
    String getSourceName();
}
