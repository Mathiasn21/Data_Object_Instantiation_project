package framework.observer.events;

import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public class ExtractorFinishedEvent implements IEvent {
    private final Object raisedBy;
    public ExtractorFinishedEvent(@NotNull Object raisedBy) {
        this.raisedBy = raisedBy;
    }

    @Override
    public @NotNull Object raisedBy() { return raisedBy; }
}
