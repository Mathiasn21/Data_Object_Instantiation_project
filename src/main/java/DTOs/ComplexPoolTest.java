package DTOs;

import doiframework.core.annotations.DataObject;

@DataObject
public class ComplexPoolTest {
    public String string;
    public String string2;

    public ComplexPoolTest(String string, String string2) {
        this.string = string;
        this.string2 = string2;
    }
}
