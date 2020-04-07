package DTOs;

import DOIFramework.annotations.DataObject;

@DataObject
public class TestJSONSimple {
    public String item;
    public String itemName;
    public String thing;

    public TestJSONSimple(String item, String itemName, String thing) {
        this.item = item;
        this.itemName = itemName;
        this.thing = thing;
    }
}
