package framework.utilities.data.read;

import org.jetbrains.annotations.NotNull;
import java.io.BufferedReader;
import java.io.IOException;

/** Interface describing a contract for reading with a String resource
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
interface IRead {
    /**
     * @param resource String
     * @return BufferedReader {@link BufferedReader}
     */
    @NotNull
    BufferedReader given(@NotNull String resource) throws IOException;
}
