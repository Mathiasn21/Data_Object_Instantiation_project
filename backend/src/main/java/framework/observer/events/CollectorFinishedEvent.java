package framework.observer.events;

import framework.collectors.ICollector;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class CollectorFinishedEvent implements IEvent {
    private final ICollector collector;
    public CollectorFinishedEvent(ICollector collector) { this.collector = collector; }

    @Override
    public @NotNull ICollector raisedBy() { return collector; }
}
