package doiframework.statistics.report;

import doiframework.statistics.calculations.Statistics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

final class StatFactory {
    @Contract(pure = true)
    @SuppressWarnings("unchecked")//Safe due to being controlled by DataReport class
    public <T extends Statistics> @NotNull T create(@NotNull Class<? extends Statistics> statClass, @NotNull Number[] ...column) throws ReflectiveOperationException {
        Class<Number[]>[] arr = genArguments((short) column.length);

        if(column.length == 1) {
            return (T) statClass.getConstructor(arr).newInstance((Object) column[0]);
        }else if (column.length == 2) {
            return (T) statClass.getConstructor(arr).newInstance((Object[]) column);
        }
        throw new ReflectiveOperationException();
    }

    /**
     * @param size short
     * @return class
     */
    @SuppressWarnings("unchecked")//safe as it is controlled inside the method
    private Class<Number[]>[] genArguments(short size){
        Class<?>[] res = new Class[size];
        for(int i = 0; i < size; i++){
            res[i] = Number[].class;
        }
        return (Class<Number[]>[]) res;
    }
}
