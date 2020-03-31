package framework.factory;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CloningFactory implements ICloningFactory{

    @Override
    public <T> T cloneObject(@NotNull T t, int number) throws CloneNotSupportedException {
        return null;//TODO: implement method
    }

    @Override
    public <T extends ICopyable> List<T> cloneObject(@NotNull T t, int number) throws CloneNotSupportedException {
        List<T> clones = new ArrayList<>(number);
        for(int i = 0; i < number; i++){
            clones.add(t.copy());
        }
        return clones;
    }
}
