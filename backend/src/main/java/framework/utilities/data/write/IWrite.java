package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;

interface IWrite {
    /**
     * @param data String
     * @return BufferedReader {@link BufferedReader}
     */
    void given(String resource, @NotNull String data) throws IOException;
}
