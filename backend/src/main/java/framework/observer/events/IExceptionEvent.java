package framework.observer.events;

import org.jetbrains.annotations.NotNull;

interface IExceptionEvent extends IEvent{
    @NotNull Exception getThrown();
}
