package framework.utilities.data;

import framework.annotations.DataObject;
import framework.utilities.data.handle.IHandle;
import framework.utilities.data.read.IRead;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class Resource {
    private final IRead readData;

    public Resource(IRead readData) {
        this.readData = readData;
    }

    public BufferedReader getData() throws FileNotFoundException {
        return readData.read();
    }
}
