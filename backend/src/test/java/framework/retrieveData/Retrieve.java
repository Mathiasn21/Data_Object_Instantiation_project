package framework.retrieveData;

import framework.utilities.data.read.IReadData;
import framework.utilities.data.read.ReadData;
import framework.utilities.data.write.IWriteData;
import framework.utilities.data.write.WriteData;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Retrieve {
    @Test
    void data_from_file() throws IOException {
        //Example of usage:
        IReadData retrieveData = new ReadData();
        retrieveData.fromFile().given("name");

        IWriteData writeData = new WriteData();
        writeData.toFile().given("","");
    }
}
