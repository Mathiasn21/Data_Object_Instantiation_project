package doiframework.core.resource.commands.write;

/**
 * Describes a contract for writing to a file.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public interface IWriteFileCommand extends IWriteCommand {
    /**
     * @param append boolean
     */
    void SetAppending(boolean append);
    void createFileIfNotExists(boolean b);
}
