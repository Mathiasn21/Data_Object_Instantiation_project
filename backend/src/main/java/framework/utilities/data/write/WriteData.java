package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class WriteData implements IWriteData{
    private final IWriteFile writeFile;
    private final IWriteURL writeURL;

    public WriteData() {
        writeFile = new WriteFile();
        writeURL = new WriteURL();
    }

    @Override
    public final @NotNull IWriteFile toFile() throws IOException {
        //TODO: implement logic
        return null;
    }

    @Override
    public final @NotNull IWriteURL toURL() throws IOException {
        //TODO: implement logic
        return null;
    }
}
