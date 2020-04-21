package doiframework.core.observer.events;

import doiframework.core.collectors.IDataCollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class DataCollectorFinishedEvent implements IEvent {
    private final IDataCollector collector;
    public DataCollectorFinishedEvent(@NotNull IDataCollector collector) {
        this.collector = collector;
    }

    @Override
    public @NotNull IDataCollector raisedBy() { return collector; }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return "DataCollectorFinishedEvent: " + collector;
    }
}
