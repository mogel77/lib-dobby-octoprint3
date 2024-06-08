package de.x8games.octolib3.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;





public class JsonStatic {

    protected static Logger logger = LogManager.getLogger("octolib3");

    private static boolean hasKey(JSONObject json, String key) {
        if (json.has(key)) return true;
        return false;
    }



    public static String jsonString(JSONObject json, String key) {
        if (!hasKey(json, key)) return "";
        return json.getString(key);
    }
    public static float jsonFloat(JSONObject json, String key) {
        if (!hasKey(json, key)) return 0.f;
        return json.getFloat(key);
    }
    public static long jsonLong(JSONObject json, String key) {
        if (!hasKey(json, key)) return 0;
        return json.getLong(key);
    }
    public static int jsonInteger(JSONObject json, String key) {
        if (!json.has(key)) return 0;
        return json.getInt(key);
    }
    public static boolean jsonBoolean(JSONObject json, String key) {
        if (!hasKey(json, key)) return false;
        return jsonString(json, key).equals("true");
    }
    public static Object jsonObject(JSONObject json, String key) {
        if (!hasKey(json, key)) return new Object();
        return json.getJSONObject(key);
    }

    public static Optional<String> optionalString(JSONObject json, String key) {
        try {
            return Optional.of(json.getString(key));
        } catch(JSONException ex) {
            return Optional.empty();
        }
    }
    public static Optional<Float> optionalFloat(JSONObject json, String key) {
        try {
            return Optional.of(json.getFloat(key));
        } catch(JSONException ex) {
            return Optional.empty();
        }
    }
    public static Optional<Long> optionalLong(JSONObject json, String key) {
        try {
            return Optional.of(json.getLong(key));
        } catch(JSONException ex) {
            return Optional.empty();
        }
    }
    public static Optional<Integer> optionalInteger(JSONObject json, String key) {
        try {
            return Optional.of(json.getInt(key));
        } catch(JSONException ex) {
            return Optional.empty();
        }
    }
    public static Optional<Boolean> optionalBoolean(JSONObject json, String key) {
        try {
            return Optional.of(json.getBoolean(key));
        } catch(JSONException ex) {
            return Optional.empty();
        }
    }

    public static List<String> jsonArrayString(JSONObject json, String key) {
        List<String> liste = new ArrayList<>();
        if (!hasKey(json, key)) return liste;
        JSONArray array = json.getJSONArray(key);
        for(Object s : array) liste.add(s.toString());
        return liste;
    }
    public static List<Integer> jsonArrayInteger(JSONObject json, String key) {
        List<Integer> liste = new ArrayList<>();
        if (!hasKey(json, key)) return liste;
        JSONArray array = json.getJSONArray(key);
        for(Object s : array) liste.add(Integer.parseInt(s.toString()));
        return liste;
    }

}
