package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public final class WriteURL implements IWriteURL {
    WriteURL() {
    }

    @Override
    public final void given(@NotNull URL resource, @NotNull String data) throws MalformedURLException {

    }

    @Override
    public final void given(String resource, @NotNull String data) throws IOException {

    }
}
