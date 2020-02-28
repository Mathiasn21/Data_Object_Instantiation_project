package framework.database;

/** Class representing valid settings
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface IDBInteraction {
    boolean createTableWithColumns();
    boolean insertRow();
    boolean alterRow();
    boolean deleteRow();
}
