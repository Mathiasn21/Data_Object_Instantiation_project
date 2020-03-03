package DTOs;

import framework.annotations.PrimaryColumn;
import framework.annotations.DataObjectField;

/**
 * ComplexDTO that contains custom field that require mapping
 */
public class ComplexDTO {

    @DataObjectField
    @PrimaryColumn
    private String string;

    @PrimaryColumn
    @DataObjectField
    private int integer;

    @PrimaryColumn
    @DataObjectField
    private double doubles;

    private String thing;
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
