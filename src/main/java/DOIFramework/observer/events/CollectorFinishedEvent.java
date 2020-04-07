package DOIFramework.observer.events;

import DOIFramework.collectors.ICollector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class CollectorFinishedEvent implements IEvent {
    private final ICollector collector;
    public CollectorFinishedEvent(@NotNull ICollector collector) {
        this.collector = collector;
    }

    @Override
    public @NotNull ICollector raisedBy() { return collector; }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return "CollectorFinishedEvent: " + collector;
    }
}
