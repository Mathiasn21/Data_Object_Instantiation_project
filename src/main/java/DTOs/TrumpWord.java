package DTOs;

import DOIFramework.core.annotations.DataConstructor;
import DOIFramework.core.annotations.DataField;
import DOIFramework.core.annotations.DataObject;
import org.jetbrains.annotations.NotNull;

@DataObject
public class TrumpWord implements Comparable<String>{
    @DataField
    public String word;

    @DataConstructor
    public TrumpWord(String word) { this.word = word; }

    @Override
    public int compareTo(@NotNull String o) {
        return this.word.compareTo(o);
    }
}
