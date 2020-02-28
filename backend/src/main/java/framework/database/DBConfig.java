package framework.database;

/** Class representing valid settings
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public abstract class DBConfig {
    public final String host, port, password, user;

    public DBConfig(String host, String port, String password, String user) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.user = user;
    }
}
