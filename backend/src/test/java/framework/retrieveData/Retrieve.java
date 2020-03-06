package framework.retrieveData;

import framework.utilities.data.retrieve.IRetrieveData;
import framework.utilities.data.retrieve.RetrieveData;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Retrieve {
    @Test
    void data_from_file() throws IOException {
        //Example of usage:
        IRetrieveData retrieveData = new RetrieveData();
        retrieveData.fromFile().from("name");
    }
}
