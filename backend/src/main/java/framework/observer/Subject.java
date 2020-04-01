package framework.observer;

import framework.observer.events.IEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
@FunctionalInterface
public interface Subject {
    void update(@NotNull IEvent event);
}
