package framework.utilities.data.write;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * This contract describes methods for easier storing/writing to resource.
 * The classes that implement this is not suppossed to contain logic related to the spesifics.
 * They are only supposed to mediate the writing of information given those classes that do handle this functionality.
 * Of course the aim here is to also implement remaining functionality where it be needed
 */
public interface IWriteData {
    @NotNull IWriteFile toFile() throws IOException;
    @NotNull IWriteURL toURL() throws IOException;
}