package framework.factory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ICloningFactory {
    <T> T cloneObject(@NotNull T t, int number) throws CloneNotSupportedException;
    <T extends ICopyable> List<T> cloneObject(@NotNull T t, int number) throws CloneNotSupportedException;
}
