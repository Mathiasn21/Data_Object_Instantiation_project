package framework.observer.events;

import framework.observer.IObservable;

public interface IEvent {
    <T> T getT();
    IObservable raisedBy();
}
