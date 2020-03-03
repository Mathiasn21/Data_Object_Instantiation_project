package DTOs;

import coll.test.PrimaryColumn;
import coll.test.PrimaryColumns;

public class DTOTest {

    @PrimaryColumns
    private final String[] thing = new String[0];


    //      OR      //


    @PrimaryColumn
    private String anString = "";
    @PrimaryColumn
    private String anString2 = "";
    @PrimaryColumn
    private String anString3 = "";
    @PrimaryColumn
    private String anString4 = "";
}
