package DTOs;

import framework.annotations.DataObject;
import framework.annotations.DataObjectField;

import java.util.Objects;

/**
 * ComplexDTO that contains custom field that require mapping
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexDTOCSV)) return false;
        ComplexDTOCSV that = (ComplexDTOCSV) o;

        if (integer != that.integer) return false;
        if (Double.compare(that.doubles, doubles) != 0) return false;
        if (!Objects.equals(string, that.string)) return false;
        return Objects.equals(thing, that.thing);
    }
}
