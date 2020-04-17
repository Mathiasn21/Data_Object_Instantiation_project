package DTOs;

import doiframework.core.annotations.DataObject;


/**
 * Class representing usage for the DataObject annotation
 */
@DataObject
public class DTO {
    public String field1;
    public int field2;
    public float field4;
    public double field3;

    public DTO(String field1, int field2, double field3, float field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }
}
