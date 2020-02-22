package API;

import framework.dataCollection.Item;

public interface IMainHandler {
    String getDataWith(String item);
    String handleRequestFrom(String request);
    String retrieveInformationFrom(Item item);
}
