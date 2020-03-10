package unitTests.framework;

import framework.utilities.data.Resource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

public class Read {

    Resource resource;
    @Test
    void from_URL_resource() throws IOException {
        //TODO: implement test
        URL url = new URL("http://example.com");
        resource = Resource.getBuilder().fromURL(url).build();
    }
    @Test
    void from_stringURL_resource() throws IOException {
        //TODO: implement test
        String URL = "http://example.com";
        resource = Resource.getBuilder().fromURL(URL).build();
    }
}
