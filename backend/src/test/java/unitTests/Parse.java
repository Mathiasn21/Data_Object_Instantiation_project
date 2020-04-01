package unitTests;

import framework.utilities.data.Parser;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class Parse {
    private final Class<?>[] wrappers = {Double.class, Float.class, Integer.class, String.class, Byte.class, Short.class, Long.class, Boolean.class};
    private final Class<?>[] primitives = {double.class, float.class, int.class, String.class, byte.class, short.class, long.class, boolean.class};

    @Test
    void from_string_to_primitive_value(){
        String[] values = {"22.34", "22.34", "2222", "String", "127", "255", "21334333332", "true"};
        for (int i = 0; i < wrappers.length; i++){
            assertSame(Parser.classToValueFromObject(wrappers[i], values[i]).getClass(), wrappers[i]);
        }
    }

    @Test
    void from_primitive_object_to_primitive_class(){
        for(int i = 0; i < wrappers.length; i++){
            assertEquals(Parser.primitiveParseFromObjectClass(wrappers[i]), primitives[i]);
        }
    }

    @Test
    void is_primitive_class_or_wrapper(){
        for(int i = 0; i < wrappers.length; i++){
            assertTrue(Parser.isPrimitiveType(wrappers[i]));
            assertTrue(Parser.isPrimitiveType(primitives[i]));
        }
    }

    @Test
    void is_not_primitive_class_or_wrapper(){
        assertFalse(Parser.isPrimitiveType(Object.class));
    }
}
