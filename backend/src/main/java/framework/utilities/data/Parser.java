package framework.utilities.data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/** Class for parsing string values to primitive types given a clazz
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public final class Parser {
    /**
     * @param clazz {@link Class}&lt;?&gt;
     * @param value String
     * @return {@link Object}
     */
    public static Object parseValueToObject(Class<?> clazz, String value) {
        if(String.class  == clazz)                         { return value; }
        if(Boolean.class == clazz || Boolean.TYPE == clazz){ return Boolean.parseBoolean(value); }
        if(Byte.class    == clazz || Byte.TYPE == clazz)   { return Byte.parseByte(value); }
        if(Short.class   == clazz || Short.TYPE == clazz)  { return Short.parseShort(value); }
        if(Integer.class == clazz || Integer.TYPE == clazz){ return Integer.parseInt(value); }
        if(Long.class    == clazz || Long.TYPE == clazz)   { return Long.parseLong(value); }
        if(Float.class   == clazz || Float.TYPE == clazz)  { return Float.parseFloat(value); }
        if(Double.class  == clazz || Double.TYPE == clazz) { return Double.parseDouble(value); }
        return value;
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public static Object[] parseToArray(@NotNull Class<?> clazz, @NotNull String value){
        //TODO: implement ability to parse from a string eg: [2,4,5,6,7,7] || [2.0,3.9] to the array
        return new Object[0];
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static Class<?> primitiveParseFromObjectClass(@NotNull Class<?> clazz){
        return clazz == Double.class    ?      double.class    :
               clazz == Float.class     ?      float.class     :
               clazz == Integer.class   ?      int.class       :
               clazz == Long.class      ?      long.class      :
               clazz == Boolean.class   ?      boolean.class   :
               clazz == Short.class     ?      short.class     :
               clazz == Byte.class      ?      byte.class      :
               clazz;
    }
}
