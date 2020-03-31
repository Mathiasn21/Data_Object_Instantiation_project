package framework.observer;

import framework.observer.events.ExceptionEvent;
import framework.observer.events.IEvent;
import framework.observer.events.EventCommand;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class EventObserver {
    private static final Map<Class<? extends IEvent>, List<Subject>> registeredEvents = new HashMap<>();
    private static final Map<Subject, Map<Class<? extends IEvent>, EventCommand>> registeredEventCommands = new HashMap<>();
    private static final Map<IObservable, List<IEvent>> observedEvents = new HashMap<>();

    static{
        registeredEvents.put(IEvent.class, new ArrayList<>());
        registeredEvents.put(ExceptionEvent.class, new ArrayList<>());
    }

    public static void observeEvents(Subject subject, Class<? extends IEvent> event){
        registeredEvents.get(event).add(subject);
    }

    public static <E extends IEvent> void registerEventFrom(IObservable observable, E event){
        if(observedEvents.containsKey(observable)) {
            observedEvents.get(observable).add(event);
            return;
        }
        List<IEvent> events = new ArrayList<>();
        events.add(event);
        observedEvents.put(observable, events);
        notifyAllSubjects(event);
    }

    public static void registerEventHandlerFor(Subject subject, EventCommand command, Class<? extends IEvent> event){
        Map<Class<? extends IEvent>, EventCommand> map = new HashMap<>();
        map.put(event, command);
        registeredEventCommands.put(subject, map);
    }

    private static void notifyAllSubjects(@NotNull IEvent event){
        registeredEvents.get(event.getClass()).iterator().forEachRemaining((subject) -> {
            executeCommand(subject, event);
            subject.update(event);
        });
    }

    private static void executeCommand(@NotNull Subject subject, @NotNull IEvent event){
        var map = registeredEventCommands.get(subject);
        var eventClazz = event.getClass();
        if (map == null || !map.containsKey(eventClazz)) { return; }

        map.get(eventClazz).invoke();
    }

}
