package framework.database;

/** Class interacting with a db
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class DBInteraction implements IDBInteraction {
    @Override
    public boolean createTableWithColumns() {
        return false;
    }

    @Override
    public boolean insertRow() {
        return false;
    }

    @Override
    public boolean alterRow() {
        return false;
    }

    @Override
    public boolean deleteRow() {
        return false;
    }
}
