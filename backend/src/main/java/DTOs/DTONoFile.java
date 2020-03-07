package DTOs;

import framework.annotations.DataObject;

@DataObject
public class DTONoFile {
    public String string;

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
