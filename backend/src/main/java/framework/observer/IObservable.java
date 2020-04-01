package framework.observer;

import framework.observer.events.IEvent;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
@FunctionalInterface
public interface IObservable {
    void raise(IEvent event);
}
