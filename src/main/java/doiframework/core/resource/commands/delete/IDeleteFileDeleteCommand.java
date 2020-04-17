package doiframework.core.resource.commands.delete;

import java.io.File;

/** Interface for deletion of files.
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public interface IDeleteFileDeleteCommand{
    void deleteFileIfExists(File file);
}
