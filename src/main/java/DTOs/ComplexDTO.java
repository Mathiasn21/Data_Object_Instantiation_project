package DTOs;

import DOIFramework.core.annotations.DataObject;
import DOIFramework.core.annotations.DataField;

/**
 * ComplexDTO that contains custom field that require mapping
 */
@DataObject
public class ComplexDTO {

    @DataField
    public int integer;

    @DataField
    public String string;

    private String thing = "dada";

    @DataField
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
