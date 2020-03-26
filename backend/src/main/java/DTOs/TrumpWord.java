package DTOs;

import framework.annotations.DataConstructor;
import framework.annotations.DataObject;
import framework.annotations.DataObjectField;

@DataObject(resourceName = "trumpSpeeches.txt")
public class TrumpWord {

    @DataObjectField
    public String word;

    @DataConstructor
    public TrumpWord(String word) {
        this.word = word;
    }
}
