package framework.utilities.data;

import framework.utilities.data.read.IRead;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/** Class representing a data source
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class Resource {
    private final IRead readData;
    private final String name;

    /**
     * @param readData {@link IRead}
     */
    Resource(@NotNull IRead readData) {
        this.readData = readData;
        this.name = readData.getSourceName();
    }

    /**
     * @return {@link BufferedReader}
     * @throws FileNotFoundException FileNotFoundException
     */
    public final @NotNull BufferedReader getData() throws IOException {
        return readData.read();
    }

    /**
     * @return {@link ResourceBuilder}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull ResourceBuilder newResource(){
        return new ResourceBuilder();
    }

    public String getName() {
        return name;
    }


    /*TODO: Allow this class to be extended with its own implementations
    *  This could be done either through inheritance or a better way, through interfaces.
    * */
}
