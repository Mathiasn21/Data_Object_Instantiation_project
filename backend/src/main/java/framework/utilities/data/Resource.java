package framework.utilities.data;

import framework.utilities.data.read.IReadCommand;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/** Class representing a data source. Aka a place to fetch data from.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public final class Resource {
    private final IReadCommand readData;
    private final String name;

    /**
     * @param readData {@link IReadCommand}
     */
    Resource(@NotNull IReadCommand readData) {
        this.readData = readData;
        this.name = "";
    }

    /**
     * @return {@link BufferedReader}
     * @throws FileNotFoundException FileNotFoundException
     */
    public final @NotNull BufferedReader getData() throws IOException { return readData.execute(); }

    /**
     * @return {@link String}
     * @throws FileNotFoundException FileNotFoundException
     */
    @NotNull
    public final String getDataAsString() throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = readData.execute();
        String line;
        while((line = bufferedReader.readLine()) != null ){
            builder.append(line);
        }

        return builder.toString();
    }

    /**
     * @return {@link ResourceBuilder}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull ResourceBuilder newResource(){ return new ResourceBuilder(); }

    public String getName() { return name; }


    /*TODO: Allow this class to be extended with its own implementations
    *  This could be done either through inheritance or a better way, through interfaces.
    * */
}
