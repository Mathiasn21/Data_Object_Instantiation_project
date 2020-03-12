package DTOs;

import framework.annotations.DataObject;
import framework.annotations.DataObjectField;

/**
 * ComplexDTO that contains custom field that require mapping
 */
@DataObject(resourceName = "test")
public class ComplexDTO {

    @DataObjectField
    public int integer;

    @DataObjectField
    public String string;

    private String thing = "dada";

    @DataObjectField
    public double doubles;

    public ComplexDTO(String string, double doubles, int integer) {
        this.integer = integer;
        this.string = string;
        this.doubles = doubles;
    }

    public ComplexDTO(String string, int integer, double doubles) {
        this.string = string;
        this.integer = integer;
        this.doubles = doubles;
    }

    public String getString() {
        return string;
    }

    public int getInteger() {
        return integer;
    }

    public double getDoubles() {
        return doubles;
    }
}
