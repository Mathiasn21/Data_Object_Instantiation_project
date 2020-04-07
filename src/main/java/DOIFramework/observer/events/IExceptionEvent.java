package DOIFramework.observer.events;

import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
interface IExceptionEvent extends IEvent{
    @NotNull Exception getThrown();
}
