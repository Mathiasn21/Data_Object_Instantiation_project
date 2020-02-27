package DTOs;

import framework.annotations.DTOField;

/**
 * ComplexDTO that contains various data fields that are not to be mapped to
 */
public class ComplexDTO {
    @DTOField
    private String string;
    @DTOField
    private int integer;
    @DTOField
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
