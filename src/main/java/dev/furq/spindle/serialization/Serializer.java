package dev.furq.spindle.serialization;

/**
 * Serializer is an interface for deserializing strings to MyComponent objects.
 */
public interface Serializer {
    /**
     * Deserializes a string to a MyComponent.
     *
     * @param input The input string to deserialize.
     * @return The deserialized MyComponent.
     */
    MyComponent deserialize(String input);
}