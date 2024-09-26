package dev.furq.spindle.serialization;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * LegacySerializer is a serializer that uses the Adventure API's legacy format.
 */
public class LegacySerializer implements Serializer {

    /**
     * Constructs a new LegacySerializer.
     */
    public LegacySerializer() {
        // Default constructor
    }

    /**
     * Deserializes a string to a MyComponent using the legacy format.
     *
     * @param input The input string to deserialize.
     * @return The deserialized MyComponent.
     */
    @Override
    public MyComponent deserialize(String input) {
        Component component = LegacyComponentSerializer.legacyAmpersand().deserialize(input);
        return new AdventureComponent(component);
    }
}