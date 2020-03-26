package DTOs;

import framework.annotations.CSV;
import framework.annotations.DataObject;
import framework.annotations.JSON;
import framework.utilities.data.Resource;

@DataObject
public class TestJSON {
    public final String item;
    public String itemName;
    public String thing;
    public int[] array;

    public TestJSON(String item, String itemName, String thing, int[] array) {
        this.item = item;
        this.itemName = itemName;
        this.thing = thing;
        this.array = array;
    }
}
