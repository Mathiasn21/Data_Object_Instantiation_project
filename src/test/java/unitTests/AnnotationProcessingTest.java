package unitTests;

import DTOs.DTO;
import DTOs.TrumpWord;
import doiframework.core.annotations.AnnotationsProcessor;
import doiframework.core.annotations.ObjectInformation;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.JSONHandler;
import org.junit.jupiter.api.Test;
import DTOs.ShowAPIDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class AnnotationProcessingTest {
    private static final String field1 = "This string";
    private static final int field2 = 22;
    private static final double field3 = 1.24;
    private static final float field4 = 1203.2f;

    @Test
    void object_instantiation(){
        ArrayList<Object[]> list = new ArrayList<>();
        Object[] arr = {field1, field2, field3, field4};
        int numObjects = 5000;

        for(int i = 0; i < numObjects; i++){
            list.add(arr);
        }

        AnnotationsProcessor annotationsProcessor = new AnnotationsProcessor();
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObjects(list, "name"));
        try {
            ObjectInformation objectInformation= annotationsProcessor.initializeDataObjects(list, "name");
            List<Object> DTO = objectInformation.data;
            for(Object o : DTO){
                assertTrue(o instanceof DTO);
            }
            assertEquals(DTO.size(), numObjects);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void dataObject_instantiation_with_fields(){
        ArrayList<Object[]> list = new ArrayList<>();
        Object[] arr = {"string", 22, 42.054};
        int numObjects = 50;

        for(int i = 0; i < numObjects; i++){
            list.add(arr);
        }

        AnnotationsProcessor annotationsProcessor = new AnnotationsProcessor();
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObjects(list, "test"));
        try {
            ObjectInformation objectInformation= annotationsProcessor.initializeDataObjects(list, "test");
            List<Object> complexDTO = objectInformation.data;

            for(Object o : complexDTO){
                assertTrue(o instanceof ShowAPIDTO);
            }
            assertEquals(complexDTO.size(), numObjects);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void dataObject_instantiation_no_specified_file(){
        ArrayList<Object[]> list = new ArrayList<>();
        Object[] sample = new Object[]{"word"};

        int numObjects = 50;
        for(int i = 0; i < numObjects; i++){
            list.add(sample);
        }

        AnnotationsProcessor annotationsProcessor = new AnnotationsProcessor();
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObjects(list, "dd"));
        try {
            ObjectInformation objectInformation= annotationsProcessor.initializeDataObjects(list, "dd");
            List<Object> noFiles = objectInformation.data;

            for(Object o : noFiles){
                assertTrue(o instanceof TrumpWord);
            }
            assertEquals(noFiles.size(), numObjects);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }


    @Test
    void dataObject_instantiation_no_specified_test() throws IOException {
        String path = System.getProperty("user.dir") + "/files/showcaseAPI.json";
        DataSource dataSource = DataSource.newResource().fromFile(path).build();
        JSONHandler jsonHandler = new JSONHandler();
        List<Object[]> list = jsonHandler.handle(dataSource.getData());

        AnnotationsProcessor annotationsProcessor = new AnnotationsProcessor();
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObjects(list, "showcaseAPI.json"));

        try {
            ObjectInformation objectInformation= annotationsProcessor.initializeDataObjects(list, "showcaseAPI.json");
            List<Object> noFiles = objectInformation.data;

            for(Object o : noFiles){ assertTrue(o instanceof ShowAPIDTO); }
        } catch (ReflectiveOperationException e) {e.printStackTrace();}

    }
}
