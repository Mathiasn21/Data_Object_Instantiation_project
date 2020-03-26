package DTOs;

import framework.annotations.CSV;
import framework.annotations.JSON;

@CSV(sources = {"https://source", "file.csv"})
@JSON(sources = "file.json")
public class ComplexPoolTest {
    public String string;

    public ComplexPoolTest(String string) { this.string = string; }
}
