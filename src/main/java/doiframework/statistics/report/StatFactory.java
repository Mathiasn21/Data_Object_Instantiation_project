package doiframework.statistics.report;

import doiframework.statistics.calculations.Statistics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

final class StatFactory {
    @Contract(pure = true)
    @SuppressWarnings("unchecked")//Due to being controlled by CentralCommand class
    public <T extends Statistics> T create(@NotNull Class<? extends Statistics> statClass, Double[] column) throws ReflectiveOperationException {
        return (T) statClass.getConstructor(Double[].class).newInstance((Object) column);
    }
}
