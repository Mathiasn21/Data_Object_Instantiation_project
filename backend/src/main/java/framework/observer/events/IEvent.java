package framework.observer.events;

import framework.observer.IObservable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public interface IEvent {
    @NotNull IObservable raisedBy();
}
