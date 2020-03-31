package framework.observer;

import framework.observer.events.IEvent;
import framework.observer.events.EventCommand;

import java.util.*;

public final class EventObserver {
    private static final Map<Class<? extends IEvent>, List<Subject>> registeredEvents = new HashMap<>();
    private static final Map<Subject, Map<IEvent, EventCommand>> registeredEventCommands = new HashMap<>();
    private static final Map<IObservable, List<IEvent>> observedEvents = new HashMap<>();


    static{
        registeredEvents.put(IEvent.class, new ArrayList<>());
    }

    public static void observeEvents(Subject subject, Class<? extends IEvent> event){
        registeredEvents.get(event).add(subject);
    }

    public static <E extends IEvent> void registerEvent(IObservable observable, E event){
        if(observedEvents.containsKey(observable)) {
            observedEvents.get(observable).add(event);
            return;
        }
        List<IEvent> events = new ArrayList<>();
        events.add(event);
        observedEvents.put(observable, events);
        notifyAllSubjects(event);
    }

    private static void notifyAllSubjects(IEvent event){

    }


    private static <E extends IEvent> void registerEventHandlerFor(Subject subject, EventCommand command, E event){
    }
}
