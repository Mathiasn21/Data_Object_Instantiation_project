package framework.dataCollection.factory;

import framework.dataCollection.ICollector;

public interface ICollectorFactory<T extends ICollector> {
    T createCollectorFrom(String fileExtension);
    T createCollectorFrom(Class<T> clazz);
}
