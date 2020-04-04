package framework.utilities.data.delete;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
@FunctionalInterface
public interface IDeleteCommand {
    void execute(@NotNull String data) throws IOException;
}
