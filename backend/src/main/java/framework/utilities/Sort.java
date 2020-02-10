package framework.utilities;
import framework.dataCollection.Collector;
import framework.dataCollection.ICollector;

public abstract class Sort implements ICollector{

    String[][] dataToSortAll = getAllColumns();
    String[] dataToSortPrimary = getAllPrimaryColumns();


    private static String sortAllAscending(String[][] dataToSort) {
        return null;
    }

    private static String sortAllDescending(String[][] dataToSort){
        return null;
    }

    private static String sortPrimaryAscending(String[] dataToSort) {
        return null;
    }

    private static String sortPrimaryDescending(String[] dataToSort){
        return null;
    }
}
