package framework.utilities.data.read;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;

public final class ReadData implements IRetrieveData {
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
