package DTOs;

import framework.annotations.CSV;
import framework.annotations.DataObject;
import framework.annotations.JSON;

@DataObject
@CSV(sources = {"https://source", "file.csv"})
@JSON(sources = "file.json")
public class ComplexPoolTest {
    public String string;
    public String string2;

    public ComplexPoolTest(String string, String string2) {
        this.string = string;
        this.string2 = string2;
    }
}
