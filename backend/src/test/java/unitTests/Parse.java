package unitTests;

import framework.utilities.data.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parse {
    @Test
    void from_string_to_array(){

    }

    @Test
    void from_primitive_object_to_primitive_class(){
        Class<?>[] classes = {Double.class, Float.class, Integer.class, String.class, Byte.class, Short.class, Long.class, Boolean.class};
        Class<?>[] expected = {double.class, float.class, int.class, String.class, byte.class, short.class, long.class, boolean.class};

        for(int i = 0; i < classes.length; i++){
            assertEquals(Parser.primitiveParseFromObjectClass(classes[i]), expected[i]);
        }
    }
}
