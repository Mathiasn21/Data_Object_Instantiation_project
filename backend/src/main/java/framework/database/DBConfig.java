package framework.database;

public abstract class DBConfig {
    public final String host, port, password, user;

    public DBConfig(String host, String port, String password, String user) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.user = user;
    }
}
