package DTOs;

import framework.annotations.DataObject;


/**
 * Class representing usage for the DataObject annotation
 */

@DataObject(fileName = "name", primaryColumnNames = {"1", "2", "3", "4"}, informationalRows = {"row1", "row2", "row3"})
public class DTOs {
    public String field1;
    public int field2;
    public double field3;
    public float field4;

    public DTOs(String field1, int field2, double field3, float field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    @Override
    public String toString() {
        return "DTOs{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                ", field3=" + field3 +
                ", field4=" + field4 +
                '}';
    }
}
