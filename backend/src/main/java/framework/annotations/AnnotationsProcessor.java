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
public class AnnotationsProcessor implements IAnnotationsProcessor{
    private final List<Class<?>> primaryColumnTypes = new ArrayList<>();
    private final Map<String, Class<?>> map = new HashMap<>();

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
    @Override
    public final Object initializeDataObject(@NotNull Object ...initArgs) throws InstantiationException {
        Set<Class<?>> dataObjectSet = getAllClassesWith(DataObject.class);
        setupPrimaryColumnsTypes(dataObjectSet);
        Constructor<?> constructor = getCorrespondingConstructor(dataObjectSet);
        return initializeDataObject(constructor, initArgs);
    }

    @Override
    public List<?> initializeDataObjects(@NotNull List<List<Object>> listWithInitArgs, @NotNull String file) throws InstantiationException {
        //TODO: Check
        return null;
    }


    /*
    public final List<Object> initializeDataObjects(List<List<Object>> initArgs)throws InstantiationException{
        Set<Class<?>> dataObjectSet = getAllClassesWith(DataObject.class);
        setupPrimaryColumnsTypes(dataObjectSet);
        Constructor<?> constructor = getCorrespondingConstructor(dataObjectSet);
        return initializeDataObject(constructor, initArgs);
    }
    */

    private void setupPrimaryColumnsTypes(Set<Class<?>> dataObjectSet) {
        if(primaryColumnTypes.size() != 0){return;}
        dataObjectSet.iterator().forEachRemaining((object) -> {
            DataObject dataObject = object.getAnnotation(DataObject.class);
            System.out.println(dataObject.fileName());
            System.out.println(Arrays.toString(dataObject.primaryKeys()));
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
            return constructor.newInstance(initArgs);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new InstantiationException("Unable to create such object");
    }



}
