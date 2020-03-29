package framework.factory;

public interface ICopyable {
    <T> T copy() throws CloneNotSupportedException;
}
