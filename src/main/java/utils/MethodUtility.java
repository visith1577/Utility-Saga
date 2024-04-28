package utils;

public class MethodUtility {

    public static Object convertRequestParamValue(Object value, Class<?> clasz){
        if (value == null || clasz == null) {
            throw new IllegalArgumentException("String value and clasz must not be null");
        }
        if(clasz == String.class){
            return value == null ? "" : ((String) value).trim();
        }else if(clasz == Double.class){
            return value == null ? 0.00 : Double.parseDouble(((String) value).trim());
        } else if(clasz == Integer.class){
            return value == null ? 0 : Integer.parseInt(((String) value).trim());
        } else if(clasz == Boolean.class){
            return value == null ? false : Boolean.parseBoolean(((String) value).trim());
        }
        return null;
    }
}
