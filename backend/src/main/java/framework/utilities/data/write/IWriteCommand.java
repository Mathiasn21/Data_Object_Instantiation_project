package framework.utilities.data.write;

import java.io.IOException;
/**
 * Describes a contract for executing commands for a resource.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
@FunctionalInterface
public interface IWriteCommand {
    /**
     * @throws IOException {@link IOException} IOException
     */
    void execute() throws IOException;
}
