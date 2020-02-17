package framework.display;

import framework.dataCollection.Item;

public interface IView {
    String getData();
    String displayChart();
    String displayDetailedInformation(Item item);
    String getAndShowComparison(Item item1, Item item2);
    String editInformation();
}
