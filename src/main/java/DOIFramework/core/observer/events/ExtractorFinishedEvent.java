package DOIFramework.core.observer.events;

import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class ExtractorFinishedEvent implements IEvent {
    private final Object raisedBy;
    public ExtractorFinishedEvent(@NotNull Object raisedBy) {
        this.raisedBy = raisedBy;
    }

    @Override
    public final @NotNull Object raisedBy() { return raisedBy; }
}
