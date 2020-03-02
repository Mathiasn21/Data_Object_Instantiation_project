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
        Reflections reflections = new Reflections("");
        Set<Class<?>> set = reflections.getTypesAnnotatedWith(DataObject.class);
        Object[] arr = set.toArray();
        System.out.println(arr[0].getClass().toString());
        Class<DataObjectStubb> object = (Class<DataObjectStubb>) arr[0].getClass().cast(DataObjectStubb.class);
        System.out.println(arr[0].getClass().getCanonicalName());

        try {
            Constructor<DataObjectStubb> constructor = object.getConstructor(String.class, int.class, float.class, long.class);
            DataObjectStubb stubb = constructor.newInstance(field1, field2, field3, field4);
            System.out.println(stubb);

            System.out.println(constructor);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    void fields_initialization(){

    }
}
