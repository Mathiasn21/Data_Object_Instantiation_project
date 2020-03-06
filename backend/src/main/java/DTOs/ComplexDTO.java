package DTOs;

import framework.annotations.DataConstructor;
import framework.annotations.DataObjectField;

/**
 * ComplexDTO that contains custom field that require mapping
 */
public class ComplexDTO {

    @DataObjectField(primaryColumnName = "name")
    private String string;

    @DataObjectField
    private int integer;

    @DataObjectField
    private double doubles;

    private String thing;

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
