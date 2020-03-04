package framework.annotations;

import framework.exceptions.NoSuchConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Class responsible for handling all processing related to annotations.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class AnnotationsProcessor {
    private final List<Class<?>> primaryColumnTypes = new ArrayList<>();

    public AnnotationsProcessor() {

    }

    /**
     * @param primitives List&lt;Class&lt;?&gt;&gt;
     */
    public AnnotationsProcessor(List<Class<?>> primitives) {
        primaryColumnTypes.addAll((primitives));
    }

    /**
     * @param clazz &lt;? extends {@link Annotation}&gt;
     * @return Set&lt;Class&lt;?&gt;&gt;
     */
    protected final Set<Class<?>> getAllClassesWith(Class<? extends Annotation> clazz){
        Reflections reflections = new Reflections("");
        return reflections.getTypesAnnotatedWith(clazz);
    }


    /**
     * Returns first matching constructor
     * @param dataObjectSet Set&lt;Class&lt;?&gt;&gt;
     * @return Constructor&lt;?&gt;
     */
    @NotNull
    private Constructor<?> getCorrespondingConstructor(@NotNull Set<Class<?>> dataObjectSet) {
        for(Class<?> clazz : dataObjectSet){
            Constructor<?>[] constructors = clazz.getConstructors();

            constructorLoop: for (Constructor<?> constructor : constructors) {
                Class<?>[] params = constructor.getParameterTypes();
                if(params.length != primaryColumnTypes.size()){
                    continue;
                }

                for(int i = 0; i < params.length; i++){
                    Class<?> param = params[i];
                    if(param != primaryColumnTypes.get(i)){
                        continue constructorLoop;
                    }
                }
                return constructor;
            }
        }
        throw new NoSuchConstructor();
    }


    /**
     * @param initArgs ...Object
     * @return Object
     * @throws InstantiationException {@link InstantiationException} InstantiateException
     */
    @NotNull
    @Contract(value = "_-> new")
    public final Object initializeDataObject(@NotNull Object ...initArgs) throws InstantiationException {
        Set<Class<?>> dataObjectSet = getAllClassesWith(DataObject.class);
        setupPrimaryColumnsTypes(dataObjectSet);
        Constructor<?> constructor = getCorrespondingConstructor(dataObjectSet);
        return initializeDataObject(constructor, initArgs);
    }

    private void setupPrimaryColumnsTypes(Set<Class<?>> dataObjectSet) {
        if(primaryColumnTypes.size() != 0){return;}
        dataObjectSet.iterator().forEachRemaining((object) -> {
            DataObject dataObject = (DataObject) object.cast(DataObject.class);
            System.out.println(Arrays.toString(dataObject.primaryColumns()));
        });
    }


    /**
     * @param constructor {@link Constructor}&lt;?&gt;
     * @param initArgs ...Object
     * @return Object
     * @throws InstantiationException InstantiationException
     */
    @NotNull
    private Object initializeDataObject(@NotNull Constructor<?> constructor, @NotNull Object ...initArgs) throws InstantiationException {
        try {
            Object o = constructor.newInstance(initArgs);
            System.out.println(o);
            return 0;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new InstantiationException("Unable to create such object");
    }
}
