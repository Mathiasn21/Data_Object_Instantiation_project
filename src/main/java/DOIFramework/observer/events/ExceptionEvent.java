package DOIFramework.observer.events;

import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class ExceptionEvent implements IExceptionEvent {
    private final Object observable;
    private final Exception exception;

    public ExceptionEvent(@NotNull Object observable, @NotNull Exception exception) {
        this.observable = observable;
        this.exception = exception;
    }

    @NotNull
    @Override
    public final Object raisedBy() { return observable; }

    @NotNull
    @Override
    public final Exception getThrown() { return exception; }
}