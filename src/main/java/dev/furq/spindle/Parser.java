package dev.furq.spindle;

import dev.furq.spindle.serialization.LegacySerializer;
import dev.furq.spindle.serialization.MiniMessageSerializer;
import dev.furq.spindle.serialization.MyComponent;
import dev.furq.spindle.serialization.Serializer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Parser class provides methods for parsing and retrieving configuration
 * data.
 */
public class Parser {

    /**
     * The current serializer type used for string deserialization.
     */
    public static SerializerType serializerType = SerializerType.LEGACY;

    private final Map<String, Object> configData;

    /**
     * Constructs a new Parser with the given configuration data.
     *
     * @param configData The configuration data to be parsed.
     */
    public Parser(Map<String, Object> configData) {
        this.configData = new HashMap<>(configData);
    }

    /**
     * Creates a Parser instance from a configuration file.
     *
     * @param file The configuration file to load.
     * @return A new Parser instance with the loaded configuration data.
     */
    public static Parser fromFile(File file) {
        Map<String, Object> data = Config.loadConfigFile(file);
        return new Parser(data);
    }

    /**
     * Deserializes a string using the default serializer type.
     *
     * @param input The input string to deserialize.
     * @return The deserialized string.
     */
    public static String deserializeString(String input) {
        return parseString(input, serializerType);
    }

    /**
     * Deserializes a string using the specified serializer type.
     *
     * @param input          The input string to deserialize.
     * @param serializerType The serializer type to use.
     * @return The deserialized string.
     */
    public static String deserializeString(String input, SerializerType serializerType) {
        return parseString(input, serializerType);
    }

    private static String parseString(String input, SerializerType serializerType) {
        if (serializerType == SerializerType.NONE) {
            return input;
        }

        Serializer serializer;
        if (serializerType == SerializerType.LEGACY) {
            serializer = new LegacySerializer();
        } else {
            serializer = new MiniMessageSerializer();
        }

        MyComponent myComponent = serializer.deserialize(input);
        return myComponent.serialize();
    }

    /**
     * Retrieves a value from the configuration data.
     *
     * @param key The key to look up in the configuration data.
     * @return The value associated with the key, or null if not found.
     */
    public Object getValue(String key) {
        return getValueFromMap(configData, key);
    }

    /**
     * Retrieves a string value from the configuration data.
     *
     * @param key The key to look up in the configuration data.
     * @return The string value associated with the key, or null if not found.
     */
    public String getString(String key) {
        return getString(key, null);
    }

    /**
     * Retrieves a string value from the configuration data, with a default value.
     *
     * @param key The key to look up in the configuration data.
     * @param defaultValue The default value to return if the key is not found.
     * @return The string value associated with the key, or the default value if not found.
     */
    public String getString(String key, String defaultValue) {
        Object value = getValue(key);
        if (value instanceof String) {
            return deserializeString((String) value);
        }
        return defaultValue;
    }

    /**
     * Retrieves an integer value from the configuration data.
     *
     * @param key The key to look up in the configuration data.
     * @return The integer value associated with the key, or 0 if not found.
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * Retrieves an integer value from the configuration data, with a default value.
     *
     * @param key The key to look up in the configuration data.
     * @param defaultValue The default value to return if the key is not found.
     * @return The integer value associated with the key, or the default value if not found.
     */
    public int getInt(String key, int defaultValue) {
        Object value = getValue(key);
        return (value instanceof Integer) ? (Integer) value : defaultValue;
    }

    /**
     * Retrieves a boolean value from the configuration data.
     *
     * @param key The key to look up in the configuration data.
     * @return The boolean value associated with the key, or false if not found.
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * Retrieves a boolean value from the configuration data, with a default value.
     *
     * @param key The key to look up in the configuration data.
     * @param defaultValue The default value to return if the key is not found.
     * @return The boolean value associated with the key, or the default value if not found.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        Object value = getValue(key);
        return (value instanceof Boolean) ? (Boolean) value : defaultValue;
    }

    /**
     * Retrieves a list value from the configuration data.
     *
     * @param key The key to look up in the configuration data.
     * @return The list value associated with the key, or an empty list if not found.
     */
    public List<Object> getList(String key) {
        return getList(key, new ArrayList<>());
    }

    /**
     * Retrieves a list value from the configuration data, with a default value.
     *
     * @param key The key to look up in the configuration data.
     * @param defaultValue The default value to return if the key is not found.
     * @return The list value associated with the key, or the default value if not found.
     */
    public List<Object> getList(String key, List<Object> defaultValue) {
        Object value = getValue(key);
        if (value instanceof List<?>) {
            List<Object> list = new ArrayList<>();
            for (Object item : (List<?>) value) {
                if (item instanceof String) {
                    list.add(deserializeString((String) item));
                } else {
                    list.add(item);
                }
            }
            return list;
        }
        return defaultValue;
    }

    /**
     * Retrieves a map value from the configuration data.
     *
     * @param key The key to look up in the configuration data.
     * @return The map value associated with the key, or an empty map if not found.
     */
    public Map<String, Object> getMap(String key) {
        Map<String, Object> result = new HashMap<>();
        Object value = getValue(key);
        if (value instanceof Map<?, ?>) {
            Map<?, ?> inputMap = (Map<?, ?>) value;
            for (Map.Entry<?, ?> entry : inputMap.entrySet()) {
                String mapKey = entry.getKey().toString();
                result.put(mapKey, entry.getValue());
            }
        }
        return result;
    }

    /**
     * Retrieves a nested map value from the configuration data.
     *
     * @param map The map to look up in the configuration data.
     * @param key The key to look up in the configuration data.
     * @return The nested map value associated with the key, or an empty map if not found.
     */
    public Map<String, Object> getNestedMap(Map<String, Object> map, String key) {
        Map<String, Object> result = new HashMap<>();
        Object value = getValueFromMap(map, key);
        if (value instanceof Map<?, ?>) {
            Map<?, ?> inputMap = (Map<?, ?>) value;
            for (Map.Entry<?, ?> entry : inputMap.entrySet()) {
                String mapKey = entry.getKey().toString();
                result.put(mapKey, entry.getValue());
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private Object getValueFromMap(Map<String, Object> map, String key) {
        String[] keys = key.split("\\.");
        Object currentValue = map;

        for (String subKey : keys) {
            if (!(currentValue instanceof Map)) {
                return null;
            }
            currentValue = ((Map<String, Object>) currentValue).get(subKey);
            if (currentValue == null) {
                return null;
            }
        }
        return currentValue;
    }
}