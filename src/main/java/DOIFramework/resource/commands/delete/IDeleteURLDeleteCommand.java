package DOIFramework.resource.commands.delete;

import org.jetbrains.annotations.NotNull;

import java.net.URL;

/** Interface for deletion of via URL request.
 * @author Robert Alexander Dankertsen: yeti-programing @ https://github.com/yeti-programing
 */
public interface IDeleteURLDeleteCommand{

    void DeleteFromURLCommand(@NotNull URL url);

    void DeleteFromURLCommand(@NotNull String url);
}
