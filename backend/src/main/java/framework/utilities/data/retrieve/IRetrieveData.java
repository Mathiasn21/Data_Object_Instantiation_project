package framework.utilities.data.retrieve;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * This contract describes methods for easier resource retrieval.
 * The classes that implement this is not suppossed to contain logic related to the spesifics.
 * They are only supposed to mediate the retrieval of information from those classes that do handle this functionality.
 * Of course the aim here is to also implement remaining functionality where it be needed
 */
public interface IRetrieveData {
    BufferedReader fromFile(@NotNull String fName) throws IOException;
    BufferedReader fromFile(@NotNull File file) throws IOException;
    BufferedReader fromURL(@NotNull String URL) throws IOException;
    BufferedReader fromURL(@NotNull URL URL) throws IOException;
}