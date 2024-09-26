package dev.furq.spindle.serialization;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * MiniMessageSerializer is a serializer that uses the Adventure API's MiniMessage format.
 */
public class MiniMessageSerializer implements Serializer {

    /**
     * Constructs a new MiniMessageSerializer.
     */
    public MiniMessageSerializer() {
        // Default constructor
    }

    /**
     * Deserializes a string to a MyComponent using the MiniMessage format.
     *
     * @param input The input string to deserialize.
     * @return The deserialized MyComponent.
     */
    @Override
    public MyComponent deserialize(String input) {
        Component component = MiniMessage.miniMessage().deserialize(input);
        return new AdventureComponent(component);
    }
}