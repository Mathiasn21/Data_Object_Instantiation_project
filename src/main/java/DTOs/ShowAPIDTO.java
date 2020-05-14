package DTOs;

import doiframework.core.annotations.DataConstructor;
import doiframework.core.annotations.DataField;
import doiframework.core.annotations.DataObject;
import org.jetbrains.annotations.NotNull;

@DataObject
public class ShowAPIDTO implements Comparable<ShowAPIDTO>{
    @DataField
    public final String string;

    @DataField
    public final int anInt;
    public final double aDouble;

    @DataConstructor
    public ShowAPIDTO(String string, int anInt, double aDouble) {
        this.string = string;
        this.anInt = anInt;
        this.aDouble = aDouble;
    }

    @Override
    public int compareTo(@NotNull ShowAPIDTO o) {
        return string.compareTo(o.string);
    }

    public String getString() {
        return string;
    }

    public int getAnInt() {
        return anInt;
    }

    public double getaDouble() { return aDouble; }
}
