package DOIFramework.resource.commands.write;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;

/**
 * Describes a contract for executing write commands on a resource.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
@FunctionalInterface
public interface IWriteCommand {
    /**
     * @param data String
     * @throws IOException {@link IOException} IOException
     */
    void execute(@NotNull String data) throws IOException;
}
