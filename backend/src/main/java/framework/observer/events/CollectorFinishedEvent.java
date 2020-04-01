package framework.observer.events;

import framework.observer.IObservable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class CollectorFinishedEvent implements IEvent {
    private final IObservable collector;
    public CollectorFinishedEvent(IObservable collector) { this.collector = collector; }

    @Override
    public @NotNull IObservable raisedBy() { return collector; }
}
