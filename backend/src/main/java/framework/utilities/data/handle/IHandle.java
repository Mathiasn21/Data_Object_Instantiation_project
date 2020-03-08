package framework.utilities.data.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface IHandle {
    void setPrimaryKeyTypes(Class<?>[] types);
    void setPrimaryKeys(String[] keys);

    List<List<Object>> handle(BufferedReader bufferedReader) throws IOException;
}
