package framework.utilities.data.retrieve;

import org.jetbrains.annotations.NotNull;
import java.io.BufferedReader;
import java.io.IOException;

interface IRead {
    /**
     * @param resource String
     * @return BufferedReader {@link BufferedReader}
     */
    @NotNull
    BufferedReader given(@NotNull String resource) throws IOException;
}
