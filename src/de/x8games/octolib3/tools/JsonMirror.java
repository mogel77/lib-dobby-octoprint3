package de.x8games.octolib3.tools;

import de.x8games.octolib3.annotations.OPiValue;
import de.x8games.octolib3.annotations.OPiValueMayBeMissing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Optional;





public class JsonMirror {

    protected static Logger logger = LogManager.getLogger("octolib3");

    protected JsonMirror(JSONObject json) {
//        logger.info(json);
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Type fieldType = field.getType();
            OPiValue annotation = field.getAnnotation(OPiValue.class);
            if (annotation != null) {
                try {
                    Object result;
                    if (field.getType() == Optional.class) {
                        result = handleValues_Optional(json, field, annotation);
                    } else {
                        result = handleValues_RAW(json, field);
                    }
                    if (result != null) {
                        field.setAccessible(true);
                        field.set(this, result);
//                        logger.info("Field: " + field.getName() + " -> " + result);
                    } else {
                        logger.error("konnte '" + field.getName() + "' nicht aus JSON parsen :: " + json);
                    }
                } catch (Exception ex) {
                    if (field.getAnnotation(OPiValueMayBeMissing.class) == null) {
                        logger.error(ex.getMessage() + " :: " + json);
                    }
                }
            }
        }
    }

    private Object handleValues_Optional(JSONObject json, Field field, OPiValue annotation) {
        String type = field.getGenericType().toString();
        if (type.equals("java.util.Optional<java.lang.String>"))      return JsonStatic.optionalString(json, field.getName());
        if (type.equals("java.util.Optional<java.lang.Float>"))       return JsonStatic.optionalFloat(json, field.getName());
        if (type.equals("java.util.Optional<java.lang.Long>"))        return JsonStatic.optionalLong(json, field.getName());
        if (type.equals("java.util.Optional<java.lang.Integer>"))     return JsonStatic.optionalInteger(json, field.getName());
        if (type.equals("java.util.Optional<java.lang.Boolean>"))     return JsonStatic.optionalBoolean(json, field.getName());
        return null;
    }

    private Object handleValues_RAW(JSONObject json, Field field) {
        if (field.getType() == String.class)        return JsonStatic.jsonString(json, field.getName());
        if (field.getType() == float.class)         return JsonStatic.jsonFloat(json, field.getName());
        if (field.getType() == long.class)          return JsonStatic.jsonLong(json, field.getName());
        if (field.getType() == int.class)           return JsonStatic.jsonInteger(json, field.getName());
        if (field.getType() == boolean.class)       return JsonStatic.jsonBoolean(json, field.getName());
        return null;
    }

}
