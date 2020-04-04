package framework.utilities.data.read;

/** Represents a command interface for reading from a File resource
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
public interface IReadFileCommand extends IReadCommand {
    @Deprecated
    String getSourceName();

    //TODO: add configuration for file settings
}
