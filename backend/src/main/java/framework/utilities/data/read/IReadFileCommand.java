package framework.utilities.data.read;

public interface IReadFileCommand extends IReadCommand {
    @Deprecated
    String getSourceName();

    //TODO: add configuration for file settings
}
