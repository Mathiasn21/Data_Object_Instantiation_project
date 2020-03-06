package framework.utilities.data.read;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;

final class ReadURL implements IReadURL{

    @Override
    public @NotNull BufferedReader given(@NotNull URL resource) throws MalformedURLException {
        //TODO: Implement logic
        return null;
    }

    @Override
    public @NotNull BufferedReader given(@NotNull String resource) {
        //TODO: Implement logic
        return null;
    }
}
