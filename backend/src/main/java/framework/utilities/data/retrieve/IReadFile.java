package framework.utilities.data.retrieve;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public interface IReadFile extends IRead{
    /**
     * @param resource String
     * @return BufferedReader {@link BufferedReader}
     * @throws IOException IOException {@link IOException}
     */
    @NotNull
    BufferedReader given(@NotNull File resource) throws IOException;
}
