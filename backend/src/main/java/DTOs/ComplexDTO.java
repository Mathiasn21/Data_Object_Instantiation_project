package DTOs;

import framework.annotations.DataConstructor;
import framework.annotations.DataObject;
import framework.annotations.DataObjectField;

/**
 * ComplexDTO that contains custom field that require mapping
 */
@DataObject(fileName = "test")
public class ComplexDTO {

    @DataObjectField
    public String string;

    @DataObjectField
    public int integer;

    @DataObjectField
    public double doubles;

    private String thing = "dada";

    @DataConstructor
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
