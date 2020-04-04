package framework.utilities.data;

import framework.utilities.data.read.IReadCommand;
import framework.utilities.data.write.IWriteCommand;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/** Class representing a data source. Aka a place to fetch data from.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.6.1
 */
public final class Resource {
    private final IReadCommand readCommand;
    private final IWriteCommand writeCommand;
    private final String[] nameSpace;
    private String data = null;

    /**
     * @param readCommand {@link IReadCommand}
     */
    Resource(@NotNull IReadCommand readCommand) {
        this(readCommand, "");
    }

    /**
     * @param writeCommand {@link IWriteCommand}
     * @param readCommand {@link IReadCommand}
     */
    Resource(@NotNull IReadCommand readCommand, @Nullable IWriteCommand writeCommand) {
        this(readCommand, writeCommand, "");
    }

    /**
     * @param readCommand {@link IReadCommand}
     * @param nameSpace {@link String}
     */
    Resource(IReadCommand readCommand, String ...nameSpace) {
        this(readCommand, null, nameSpace);
    }

    /**
     * @param writeCommand {@link IWriteCommand}
     * @param readCommand {@link IReadCommand}
     * @param nameSpace {@link String}
     */
    Resource(IReadCommand readCommand, IWriteCommand writeCommand, String ...nameSpace) {
        this.readCommand = readCommand;
        this.nameSpace = nameSpace;
        this.writeCommand = writeCommand;
    }


    /**
     * @return {@link BufferedReader}
     * @throws FileNotFoundException FileNotFoundException
     */
    public final @NotNull BufferedReader getData() throws IOException { return readCommand.execute(); }

    /**
     * @return {@link String}
     * @throws FileNotFoundException FileNotFoundException
     */
    @NotNull
    public final String getDataAsString() throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = readCommand.execute();
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

    /**
     * @return {@link String}
     */
    public final String[] getNameSpace() { return nameSpace; }


    /**
     * @return boolean
     * @throws IOException IOException {@link IOException}
     */
    public final void writeData() throws IOException { writeCommand.execute(data); }
}
