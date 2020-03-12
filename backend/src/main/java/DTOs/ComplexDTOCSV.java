package DTOs;

import framework.annotations.DataObject;
import framework.annotations.DataObjectField;

/**
 * ComplexDTO that contains custom field that require mapping
 */
@DataObject
public class ComplexDTOCSV {

    @DataObjectField
    public int integer;

    @DataObjectField(primaryColumnName = "Column1")
    public String string;


    private String thing = "dada";

    @DataObjectField
    public double doubles;

    public ComplexDTOCSV(String string, int integer, double doubles) {
        this.string = string;
        this.integer = integer;
        this.doubles = doubles;
    }

    public ComplexDTOCSV(String string, double doubles, int integer) {
        this.integer = integer;
        this.string = string;
        this.thing = thing;
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