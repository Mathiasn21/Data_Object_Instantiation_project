package framework.annotations;
import DTOs.ComplexDTO;
import DTOs.DTO;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DataObjectTest{
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

    }
}
