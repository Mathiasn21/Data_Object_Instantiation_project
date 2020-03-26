package DTOs;

import framework.annotations.DataConstructor;
import framework.annotations.DataObject;

@DataObject
public class TrumpWord {
    public String word;

    @DataConstructor
    public TrumpWord(String word) { this.word = word; }
}
