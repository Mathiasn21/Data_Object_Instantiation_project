package framework.annotations;
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
                System.out.println(o instanceof DTO);
            }
            assertEquals(DTO.size(), numObjects);
            System.out.println(DTO);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void fields_initialization(){

    }


    @Test
    void dataObject_initialization_no_spcified_file(){

    }

    @Test
    void dataObject_initialization_with_fields(){

    }
}
