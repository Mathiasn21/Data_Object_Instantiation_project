package doiframework.utilities;

import doiframework.errors.NoSuchComparatorError;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;

/** Class for parsing string values to primitive types execute a clazz
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
public final class Parser {
    /**
     * @param clazz {@link Class}&lt;?&gt;
     * @param value String
     * @return {@link Object}
     */
    public static Object toPrimitiveValueGivenType(Class<?> clazz, String value) {
        if(String.class  == clazz)                            { return value; }
        if(Integer.class == clazz || Integer.TYPE == clazz)   { return Integer.parseInt(value); }
        if(Double.class  == clazz || Double.TYPE  == clazz)   { return Double.parseDouble(value); }
        if(Boolean.class == clazz || Boolean.TYPE == clazz)   { return Boolean.parseBoolean(value); }
        if(Byte.class    == clazz || Byte.TYPE    == clazz)   { return Byte.parseByte(value); }
        if(Short.class   == clazz || Short.TYPE   == clazz)   { return Short.parseShort(value); }
        if(Long.class    == clazz || Long.TYPE    == clazz)   { return Long.parseLong(value); }
        if(Float.class   == clazz || Float.TYPE   == clazz)   { return Float.parseFloat(value); }
        return value;
    }

    /**
     * Returns corresponding primitive class or the class if none was found
     * @param clazz {@link Class}&lt;?&gt;
     * @return {@link Class}&lt;?&gt;
     */
    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static Class<?> PrimitiveWrapperToPrimitiveType(@NotNull Class<?> clazz){
        return clazz == Double.class    ?      double.class    :
               clazz == Float.class     ?      float.class     :
               clazz == Integer.class   ?      int.class       :
               clazz == Long.class      ?      long.class      :
               clazz == Boolean.class   ?      boolean.class   :
               clazz == Short.class     ?      short.class     :
               clazz == Byte.class      ?      byte.class      :
               clazz;
    }

    /**
     * Returns true if clazz is a primitive or a primitive wrapper
     * @param clazz {@link Class}&lt;?&gt;
     * @return boolean
     */
    @Contract(pure = true)
    public static boolean isPrimitiveType(@NotNull Class<?> clazz){
        return  (clazz == String.class)                            ||
                (clazz == Double.class  || clazz == double.class ) ||
                (clazz == Float.class   || clazz == float.class  ) ||
                (clazz == Integer.class || clazz == int.class    ) ||
                (clazz == Long.class    || clazz == long.class   ) ||
                (clazz == Boolean.class || clazz == boolean.class) ||
                (clazz == Short.class   || clazz == short.class  ) ||
                (clazz == Byte.class    || clazz == byte.class   );
    }

    /**
     * Returns true if clazz is a primitive number or a primitive wrapper number
     * @param clazz {@link Class}&lt;?&gt;
     * @return boolean
     */
    @Contract(pure = true)
    public static boolean isPrimitiveNumber(@NotNull Class<?> clazz){
        return  (clazz == Double.class  || clazz == double.class ) ||
                (clazz == Float.class   || clazz == float.class  ) ||
                (clazz == Integer.class || clazz == int.class    ) ||
                (clazz == Short.class   || clazz == short.class  ) ||
                (clazz == Byte.class    || clazz == byte.class   );
    }


    /**
     * Gets corresponding comparator for given primitive type,
     * throws error if none is found. It is an error as this is
     * considered fatal if no comparator was found.
     * @param clazz {@link Class}&lt;?&gt;
     * @return {@link Comparator}&lt;?&gt;
     */
    @NotNull
    public static Comparator<?> getComparatorForPrimitive(@NotNull Class<?> clazz) {
        if(String.class  == clazz)                            { return (Comparator<String>)  String::compareTo; }
        if(Integer.class == clazz || Integer.TYPE == clazz)   { return (Comparator<Integer>) Integer::compareTo; }
        if(Double.class  == clazz || Double.TYPE  == clazz)   { return (Comparator<Double>)  Double::compareTo; }
        if(Boolean.class == clazz || Boolean.TYPE == clazz)   { return (Comparator<Boolean>) Boolean::compareTo; }
        if(Byte.class    == clazz || Byte.TYPE    == clazz)   { return (Comparator<Byte>)    Byte::compareTo; }
        if(Short.class   == clazz || Short.TYPE   == clazz)   { return (Comparator<Short>)   Short::compareTo; }
        if(Long.class    == clazz || Long.TYPE    == clazz)   { return (Comparator<Long>)    Long::compareTo; }
        if(Float.class   == clazz || Float.TYPE   == clazz)   { return (Comparator<Float>)   Float::compareTo; }
        throw new NoSuchComparatorError("No such comparator exists for class: " + clazz.getName());
    }
}
