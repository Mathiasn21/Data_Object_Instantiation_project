package framework.utilities;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public interface IRetrieveData {
    BufferedReader readDataFromFile(@NotNull String fName) throws IOException;
    BufferedReader readDataFromFile(@NotNull File file) throws IOException;
    BufferedReader readDataFromURL(@NotNull String URL) throws IOException;
    BufferedReader readDataFromURL(@NotNull URL URL) throws IOException;
}
