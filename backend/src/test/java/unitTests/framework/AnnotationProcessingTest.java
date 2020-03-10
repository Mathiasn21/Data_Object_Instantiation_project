package unitTests.framework;
import DTOs.ComplexDTO;
import DTOs.DTO;
import DTOs.DTONoFile;
import framework.annotations.AnnotationsProcessor;
import framework.utilities.data.Resource;
import framework.utilities.data.handle.JSONHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnnotationProcessingTest {
    private static final String field1 = "This string";
    private static final int field2 = 22;
    private static final double field3 = 1.24;
    private static final float field4 = 1203.2f;

    @Test
    void object_instantiation(){
        ArrayList<Object[]> list = new ArrayList<>();
        Object[] arr = {field1, field2, field3, field4};
        int numObjects = 50;

        for(int i = 0; i < numObjects; i++){
            list.add(arr);
        }

        AnnotationsProcessor annotationsProcessor = new AnnotationsProcessor();
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObjectsFromFileName(list, "name"));
        try {
            List<Object> DTO = annotationsProcessor.initializeDataObjectsFromFileName(list, "name");
            for(Object o : DTO){
                assertTrue(o instanceof DTO);
            }
            assertEquals(DTO.size(), numObjects);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
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
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObjectsFromFileName(list, "test"));
        try {
            List<Object> complexDTO = annotationsProcessor.initializeDataObjectsFromFileName(list, "test");
            List<ComplexDTO> test = new ArrayList<>();

            for(Object o : complexDTO){
                assertTrue(o instanceof ComplexDTO);
                test.add((ComplexDTO) o);
            }
            assertEquals(complexDTO.size(), numObjects);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void dataObject_instantiation_no_specified_file(){
        ArrayList<Object[]> list = new ArrayList<>();
        Object[] sample = new Object[]{"dwafesagea"};

        int numObjects = 50;
        for(int i = 0; i < numObjects; i++){
            list.add(sample);
        }

        AnnotationsProcessor annotationsProcessor = new AnnotationsProcessor();
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObjectsFromFileName(list, "dd"));
        try {
            List<Object> noFiles = annotationsProcessor.initializeDataObjectsFromFileName(list, "dd");
            for(Object o : noFiles){
                assertTrue(o instanceof DTONoFile);
            }
            assertEquals(noFiles.size(), numObjects);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    void dataObject_instantiation_no_specified_test() throws IOException {

        String path = System.getProperty("user.dir") + "/files/DTOJson.json";
        Resource resource = Resource.getBuilder().fromFile(path).build();
        JSONHandler jsonHandler = new JSONHandler();
        List<Object[]> list = jsonHandler.handle(resource.getData());

        AnnotationsProcessor annotationsProcessor = new AnnotationsProcessor();
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObjectsFromFileName(list, "DTOJson.json"));

        try {
            List<Object> noFiles = annotationsProcessor.initializeDataObjectsFromFileName(list, "dd");
            for(Object o : noFiles){ assertTrue(o instanceof DTONoFile); }
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {e.printStackTrace();}
    }
}
