package framework.annotations;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DataObjectTest{
    private static final String field1 = "This is a string";
    private static final int field2 = 22;
    private static final double field3 = 1.24;
    private static final long field4 = 120322134;

    @Test
    void object_instantiation(){
        List<Class<?>> primitiveTypes = new ArrayList<>();
        primitiveTypes.add(String.class);
        primitiveTypes.add(int.class);
        primitiveTypes.add(double.class);
        primitiveTypes.add(float.class);

        ArrayList<Object> list = new ArrayList<>();
        list.add(field1);
        list.add(field2);
        list.add(field3);
        list.add(field4);

        AnnotationsProcessor annotationsProcessor = new AnnotationsProcessor(primitiveTypes);
        assertDoesNotThrow(() -> annotationsProcessor.initializeDataObject(list.toArray()));
    }

    @Test
    void fields_initialization(){

    }
}
