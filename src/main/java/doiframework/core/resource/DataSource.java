package doiframework.core.resource;

import doiframework.core.resource.commands.read.IReadCommand;
import doiframework.core.resource.commands.write.IWriteCommand;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/** Class representing a resource source. Aka a place to fetch resource from.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.6.1
 */
public final class DataSource {
    private final IReadCommand readCommand;
    private final IWriteCommand writeCommand;
    private final String[] nameSpaces;

    /**
     * @param readCommand {@link IReadCommand}
     */
    DataSource(@NotNull IReadCommand readCommand) {
        this(readCommand, "");
    }

    /**
     * @param writeCommand {@link IWriteCommand}
     * @param readCommand {@link IReadCommand}
     */
    DataSource(@NotNull IReadCommand readCommand, @Nullable IWriteCommand writeCommand) {
        this(readCommand, writeCommand, "");
    }

    /**
     * @param readCommand {@link IReadCommand}
     * @param nameSpaces {@link String}
     */
    DataSource(IReadCommand readCommand, String ... nameSpaces) {
        this(readCommand, null, nameSpaces);
    }

    /**
     * @param writeCommand {@link IWriteCommand}
     * @param readCommand {@link IReadCommand}
     * @param nameSpaces {@link String}
     */
    DataSource(IReadCommand readCommand, IWriteCommand writeCommand, String ... nameSpaces) {
        this.readCommand = readCommand;
        this.nameSpaces = nameSpaces;
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
     * @return {@link DataSourceBuilder}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DataSourceBuilder newResource(){ return new DataSourceBuilder(); }

    /**
     * @return {@link String}
     */
    public final String[] getNameSpaces() { return nameSpaces; }
}
