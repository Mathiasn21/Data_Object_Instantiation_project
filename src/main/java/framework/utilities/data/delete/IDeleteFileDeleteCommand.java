package framework.utilities.data.delete;

import java.io.File;

/**
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public interface IDeleteFileDeleteCommand extends IDeleteCommand {
    void deleteFileIfExists(File file);
}
