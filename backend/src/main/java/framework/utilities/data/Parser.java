package framework.utilities.data;

public class Parser {
    public static Object parseValueToObject( Class<?> clazz, String value ) {
        if(String.class == clazz){ return value;}

        if(Boolean.class == clazz  || Boolean.TYPE == clazz){ return Boolean.parseBoolean(value); }
        if(Byte.class == clazz || Byte.TYPE == clazz){ return Byte.parseByte(value); }
        if(Short.class == clazz || Short.TYPE == clazz){ return Short.parseShort(value); }
        if(Integer.class == clazz || Integer.TYPE == clazz){ return Integer.parseInt(value); }
        if(Long.class == clazz || Long.TYPE == clazz){ return Long.parseLong(value); }
        if(Float.class == clazz || Float.TYPE == clazz){ return Float.parseFloat(value); }
        if(Double.class == clazz || Double.TYPE == clazz){ return Double.parseDouble(value); }
        return value;
    }
}
