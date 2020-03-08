package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Describes a contract for writing to a resource given data.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
@FunctionalInterface
interface IWrite {

    /**
     * @param resource String
     * @param data String
     * @throws IOException IOException
     */
    void given(@NotNull String resource, @NotNull String data) throws IOException;
}
