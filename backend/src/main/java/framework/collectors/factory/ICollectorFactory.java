package framework.collectors.factory;

import framework.collectors.ICollector;

public interface ICollectorFactory<T extends ICollector> {
    T createCollectorFrom(String fileExtension);
    T createCollectorFrom(Class<T> clazz);
}
