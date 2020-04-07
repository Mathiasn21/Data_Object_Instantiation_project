package DTOs;

import DOIFramework.annotations.DataObject;
import DOIFramework.annotations.DataField;

@DataObject
public class DTONoFile{

    @DataField
    public String string;

    public int anInt;

    public DTONoFile(int integer, String string) {
        this.string = string;
    }

    public DTONoFile(String string, int integer) {
        this.string = string;
    }

    public DTONoFile(int anInt) {
        this.string = "d";
    }

    public DTONoFile(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "DTONoFile{" +
                "string='" + string + '\'' +
                '}';
    }
}
