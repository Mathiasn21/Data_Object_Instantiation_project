package framework.database;

public class DBInteraction implements IDBInteraction {
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
