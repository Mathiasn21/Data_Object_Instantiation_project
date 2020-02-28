package framework.collectors.factory;

import framework.collectors.ICollector;

/**
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public interface ICollectorFactory<T extends ICollector> {
    T createCollectorFrom(String fileExtension);
    T createCollectorFrom(Class<T> clazz);
}
