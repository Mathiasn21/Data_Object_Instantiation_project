package framework.utilities.data.retrieve;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;

/**
 * This contract describes methods for easier resource retrieval.
 * The classes that implement this is not suppossed to contain logic related to the spesifics.
 * They are only supposed to mediate the retrieval of information given those classes that do handle this functionality.
 * Of course the aim here is to also implement remaining functionality where it be needed
 */
public interface IRetrieveData {
    @NotNull IReadFile fromFile() throws IOException;
    @NotNull IReadURL fromURL() throws IOException;
}