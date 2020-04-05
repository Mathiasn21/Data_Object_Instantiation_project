package framework.resource.commands.read;

import java.io.BufferedReader;
import java.io.IOException;

/** Interface describing a contract for reading with a String resource
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 2.0.0
 */
@FunctionalInterface
public interface IReadCommand {
    /**
     * @throws IOException e
     * @return {@link BufferedReader}
     */
    BufferedReader execute() throws IOException;
}
