package DOIFramework.statistics;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class StatFactory {
    @Contract(pure = true)
    @SuppressWarnings("unchecked")//Due to being controlled by CentralCommand class
    public <T extends IStatistics> T create(@NotNull Class<? extends IStatistics> statClass, Double[] column) throws ReflectiveOperationException {
        return (T) statClass.getConstructor(Double[].class).newInstance((Object) column);
    }
}
