package framework.data.commands.read;

/** Represents a command interface for reading from a URL resource
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
public interface IReadURLCommand extends IReadCommand{
    @Deprecated
    String getSourceName();

    //TODO: add configurations for URL settings
}
