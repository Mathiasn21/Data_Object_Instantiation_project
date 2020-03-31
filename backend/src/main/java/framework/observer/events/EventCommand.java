package framework.observer.events;

@FunctionalInterface
public interface EventCommand {
    void invoke();
}
