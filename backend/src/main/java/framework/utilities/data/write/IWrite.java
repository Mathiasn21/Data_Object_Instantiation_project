package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
interface IWrite {
    /**
     * @param data String
     * @return BufferedReader {@link BufferedReader}
     */
    void given(String resource, @NotNull String data) throws IOException;
}
