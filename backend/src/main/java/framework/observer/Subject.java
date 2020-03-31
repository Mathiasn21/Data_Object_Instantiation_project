package framework.observer;

import framework.observer.events.IEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Subject {
    void update(@NotNull IEvent event);
}
