package API;

import framework.dataCollection.Item;

public interface IMainHandler {
    String getData();
    String displayChart();
    String displayDetailedInformation(Item item);
    String getAndShowComparison(Item item1, Item item2);
}