package doiframework.statistics.report;

import doiframework.statistics.calculations.Statistics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

final class StatFactory {
    @Contract(pure = true)
    @SuppressWarnings("unchecked")//Safe due to being controlled by ReportCommand class
    public <T extends Statistics> @NotNull T create(@NotNull Class<? extends Statistics> statClass, @NotNull Number[] column) throws ReflectiveOperationException {
        return (T) statClass.getConstructor(Number[].class).newInstance((Object) column);
    }
}
