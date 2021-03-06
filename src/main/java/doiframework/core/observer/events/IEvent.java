package doiframework.core.observer.events;

import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public interface IEvent {
    @NotNull Object raisedBy();
}
