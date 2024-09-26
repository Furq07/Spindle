package dev.furq.spindle.serialization;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * AdventureComponent is a wrapper around the Adventure API's Component class.
 * It provides serialization functionality.
 */
public class AdventureComponent implements MyComponent {
    private final Component component;

    /**
     * Constructs an AdventureComponent with the given Component.
     *
     * @param component The Adventure API Component to wrap.
     */
    public AdventureComponent(Component component) {
        this.component = component;
    }

    /**
     * Serializes the wrapped Component to a string.
     *
     * @return The serialized string representation of the Component.
     */
    @Override
    public String serialize() {
        return LegacyComponentSerializer.legacySection().serialize(component);
    }

    /**
     * Gets the wrapped Component.
     *
     * @return The wrapped Component.
     */
    public Component getComponent() {
        return component;
    }
}