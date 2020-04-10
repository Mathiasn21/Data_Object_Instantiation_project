package DOIFramework.statistics;

import org.jetbrains.annotations.Contract;

import java.lang.reflect.InvocationTargetException;

public class StatFactory {
    @Contract(pure = true)
    @SuppressWarnings("unchecked")//Due to being controlled by CentralCommand class
    public <T extends IStatistics> T create(Class<? extends IStatistics> statClass, Double[] column) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) statClass.getConstructor(Double.class).newInstance(column);
    }
}
