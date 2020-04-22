package doiframework.core.collectors;

import doiframework.core.observer.EventObserver;
import doiframework.core.observer.events.ExceptionEvent;
import doiframework.core.resource.DataSource;
import doiframework.utilities.handlers.IDataHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mathias Walter Nilsen - Mathiasn21 - https://github.com/Mathiasn21/
 */
public final class DataCollectorPool implements IDataCollectorPool {
    private byte numberOfThreads = 2;
    private final List<IDataCollector> collectors;

    DataCollectorPool(List<IDataCollector> collectors) { this.collectors = collectors; }

    @Override
    public void collectAllData() throws IOException {
        for (IDataCollector collector : collectors) { collector.collectData(); }
    }

    /**
     * Utilizes multiple threads that does not block the main thread from executing.
     * By default this utilizes a default {@link ThreadPoolExecutor} with 2 threads
     */
    @Override
    public void collectAllDataAsync() {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
        collectAllDataAsync(pool);
    }

    /**
     * @param pool {@link ThreadPoolExecutor}
     */
    @Override
    public void collectAllDataAsync(ThreadPoolExecutor pool){
        for (IDataCollector collector : collectors) {
            pool.submit(() -> {
                try { collector.collectData(); }
                catch (IOException e) {
                    EventObserver.registerEventFrom(new ExceptionEvent(this, e));
                }
            });
        }
        pool.shutdown();
    }

    @NotNull
    @Override
    public Iterator<IDataCollector> iterator() { return collectors.iterator(); }

    @NotNull
    @Contract(pure = true)
    @Override
    public List<IDataCollector> getAllCollectors() { return Collections.unmodifiableList(collectors); }

    @Override
    public void setNumberOfThreads(byte number) { numberOfThreads = number; }

    /**
     * @param dataSources {@link List}&lt;{@link DataSource}&gt;
     * @param dataHandler {@link IDataHandler}
     * @return {@link DataCollectorBuilder}
     */
    @NotNull
    @Contract("_, _ -> new")
    public static DataCollectorPoolBuilder newCollectors(List<DataSource> dataSources, IDataHandler dataHandler) {
        return new DataCollectorPoolBuilder(dataSources, dataHandler);
    }
}