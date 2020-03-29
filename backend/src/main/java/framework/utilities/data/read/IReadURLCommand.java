package framework.utilities.data.read;

public interface IReadURLCommand extends IReadCommand{
    @Deprecated
    String getSourceName();

    //TODO: add configurations for URL settings
}
