package framework.utilities.data;

import framework.utilities.data.read.IRead;
import java.net.URL;

import java.io.File;

public class ResourceBuilder {
    private IRead read;

    public ResourceBuilder() {
    }

    public ResourceBuilder fromFile(String file) {
        return this;
    }

    public ResourceBuilder fromFile(File file) {
        return this;
    }

    public ResourceBuilder fromURL(URL file) {
        return this;
    }

    public Resource build() {
        return new Resource(read);
    }
}
