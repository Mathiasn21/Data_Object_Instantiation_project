package framework.observer;

import framework.observer.events.IEvent;

@FunctionalInterface
public interface IObservable {
    void raise(IEvent event);
}
