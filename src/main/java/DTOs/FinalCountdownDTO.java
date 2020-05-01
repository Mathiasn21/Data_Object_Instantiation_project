package DTOs;

import doiframework.core.annotations.DataField;
import doiframework.core.annotations.DataObject;

@DataObject
public class FinalCountdownDTO {

    @DataField
    public final String lyrics;
    @DataField
    public final double data1;
    @DataField
    public final double data2;

    public FinalCountdownDTO(String lyrics, double data1, double data2) {
        this.lyrics = lyrics;
        this.data1 = data1;
        this.data2 = data2;
    }

}
