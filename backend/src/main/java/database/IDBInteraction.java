package database;

/** Class representing a contract for interacting with a database
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.0
 */
public interface IDBInteraction {
    boolean createTableWithColumns();
    boolean insertRow();
    boolean alterRow();
    boolean deleteRow();
}
