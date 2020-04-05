import framework.observer.EventObserver;
import framework.observer.events.CollectorFinishedEvent;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EventObserver.subscribeToEvent(event -> System.out.println("Got event: " + event), CollectorFinishedEvent.class);
    }
}
