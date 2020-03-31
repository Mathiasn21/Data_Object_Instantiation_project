package framework.observer.events;

import framework.observer.IObservable;
import org.jetbrains.annotations.NotNull;

public interface IEvent {
    @NotNull IObservable raisedBy();
}
