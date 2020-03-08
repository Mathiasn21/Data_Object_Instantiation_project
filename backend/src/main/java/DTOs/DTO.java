package DTOs;

import framework.annotations.DataConstructor;
import framework.annotations.DataObject;


/**
 * Class representing usage for the DataObject annotation
 */
@DataObject(fileName = "name")
public class DTO {
    public String field1;
    public int field2;
    public double field3;
    public float field4;


    public DTO(String field1, int field2, int fieldd, int fielddd) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @DataConstructor
    public DTO(String field1, int field2, double field3, float field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                ", field3=" + field3 +
                ", field4=" + field4 +
                '}';
    }
}
