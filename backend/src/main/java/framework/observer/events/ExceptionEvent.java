package framework.observer.events;

import framework.observer.IObservable;
import org.jetbrains.annotations.NotNull;

public final class ExceptionEvent implements IExceptionEvent {
    private final IObservable observable;
    private final Exception exception;

    public ExceptionEvent(@NotNull IObservable observable, @NotNull Exception exception) {
        this.observable = observable;
        this.exception = exception;
    }

    @NotNull
    @Override
    public final IObservable raisedBy() { return observable; }

    @NotNull
    @Override
    public final Exception getThrown() { return exception; }
}