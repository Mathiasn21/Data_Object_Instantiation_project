package doiframework.core.observer.events;

/** Represents a command interface for reading from a File resource
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 * @version 1.0.0
 */
@FunctionalInterface
public interface EventCommand {
    void invoke();
}
