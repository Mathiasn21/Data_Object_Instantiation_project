package DTOs;

import doiframework.core.annotations.DataObject;

import java.util.Arrays;

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

    @Override
    public String toString() {
        return "TestJSON{" +
                "item='" + item + '\'' +
                ", itemName='" + itemName + '\'' +
                ", thing='" + thing + '\'' +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}
