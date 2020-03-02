package framework.annotations;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import framework.stubb.DataObjectStubb;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataObjectTest{
    private static final String field1 = "This is a string";
    private static final int field2 = 22;
    private static final float field3 = (float) 1.24;
    private static final long field4 = 120322134;

    @Test
    void object_instantiation(){

    }

    @Test
    void fields_initialization(){

    }
}
