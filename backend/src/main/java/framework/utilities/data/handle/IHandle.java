package framework.utilities.data.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/** Contract for handling data.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IHandle {
    /**
     * @param types {@link Class}&lt;?&gt;[]
     */
    void setPrimaryKeyTypes(Class<?>[] types);

    /**
     * @param keys String[]
     */
    void setPrimaryKeys(String[] keys);

    /**
     * @param bufferedReader {@link BufferedReader}
     * @return {@link List}&lt;{@link List}&lt;{@link Object}&gt;&gt;
     * @throws IOException IOException
     */
    List<List<Object>> handle(BufferedReader bufferedReader) throws IOException;
}
