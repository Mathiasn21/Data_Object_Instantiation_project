package framework.utilities.data.read;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;

/** A class used for mediating retrieval of data from a given resource
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class ReadData implements IReadData {
    private final IReadFile readFile;
    private final IReadURL readURL;

    public ReadData() {
        readFile = new ReadFile();
        readURL = new ReadURL();
    }

    @NotNull
    @Override
    public final IReadFile fromFile() throws IOException {
        //TODO: Implement logic
        return readFile;
    }

    @NotNull
    @Override
    public final IReadURL fromURL() throws IOException {
        //TODO: Implement logic
        return readURL;
    }
}
