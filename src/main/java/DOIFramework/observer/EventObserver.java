package DOIFramework.observer;

import DOIFramework.observer.events.CollectorFinishedEvent;
import DOIFramework.observer.events.ExceptionEvent;
import DOIFramework.observer.events.IEvent;
import DOIFramework.observer.events.EventCommand;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Mathias - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class EventObserver {
    private static final Map<Class<? extends IEvent>, List<Subject>> registeredEvents = new HashMap<>();
    private static final Map<Subject, Map<Class<? extends IEvent>, EventCommand>> registeredEventCommands = new HashMap<>();
    private static final Map<Object, List<IEvent>> observedEvents = new HashMap<>();

    EventObserver(){}

    static{
        registeredEvents.put(IEvent.class, new ArrayList<>());
        registeredEvents.put(ExceptionEvent.class, new ArrayList<>());
        registeredEvents.put(CollectorFinishedEvent.class, new ArrayList<>());
    }

    public static void subscribeToEvents(@NotNull Subject subject, @NotNull List<Class<? extends IEvent>> events){
        for (Class<? extends IEvent> event : events) {
            subscribeToEvent(subject, event);
        }
    }

    public static void subscribeToEvent(@NotNull Subject subject, @NotNull Class<? extends IEvent> event){
        List<Subject> subjects = registeredEvents.get(event);
        if(subjects == null || subjects.contains(subject)){ return; }
        subjects.add(subject);
    }

    public static <E extends IEvent> void registerEventFrom(@NotNull E event){
        Object observable = event.raisedBy();
        if(observedEvents.containsKey(observable)) {
            observedEvents.get(observable).add(event);
            return;
        }
        List<IEvent> events = new ArrayList<>();
        events.add(event);
        observedEvents.put(observable, events);
        notifyAllSubjects(event);
    }

    public static void registerEventHandlerFor(@NotNull Subject subject, @NotNull EventCommand command, @NotNull Class<? extends IEvent> event){
        Map<Class<? extends IEvent>, EventCommand> map = new HashMap<>();
        map.put(event, command);
        registeredEventCommands.put(subject, map);
    }

    private static void notifyAllSubjects(@NotNull IEvent event){
        Class<? extends IEvent> eventClazz = event.getClass();
        List<Subject> subjects = registeredEvents.get(eventClazz);
        if(subjects == null || subjects.size() == 0){ return; }
        subjects.iterator().forEachRemaining((subject) -> {
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
