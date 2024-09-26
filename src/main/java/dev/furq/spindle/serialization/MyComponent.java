package dev.furq.spindle.serialization;

/**
 * MyComponent is an interface for components that can be serialized to a string.
 */
public interface MyComponent {
    /**
     * Serializes the component to a string.
     *
     * @return The serialized string representation of the component.
     */
    String serialize();
}