package doiframework.utilities.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/** Contract for handling resource.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public interface IHandleData {
    /**
     * @param bufferedReader {@link BufferedReader}
     * @return {@link List}&lt;{@link List}&lt;{@link Object}&gt;&gt;
     * @throws IOException IOException
     */
    List<Object[]> handle(BufferedReader bufferedReader) throws IOException;
}