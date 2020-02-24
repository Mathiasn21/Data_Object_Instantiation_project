package framework.database;

public interface IDBInteraction {
    boolean createTableWithColumns();
    boolean insertRow();
    boolean alterRow();
    boolean deleteRow();
}
