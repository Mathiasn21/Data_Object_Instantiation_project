package DTOs;

import framework.annotations.DataObject;
import framework.annotations.DataObjectField;
import org.jetbrains.annotations.NotNull;

@DataObject
public class DTONoFile implements Comparable<DTONoFile>{
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


    @Override
    public int compareTo(@NotNull DTONoFile o) {
        return this.string.compareTo(o.string);
    }
}
