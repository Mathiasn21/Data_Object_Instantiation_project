package DTOs;

import framework.annotations.DataObject;
import framework.annotations.DataObjectField;

@DataObject
public class DTONoFile {
    public int anInt;

    @DataObjectField
    public String string;

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
