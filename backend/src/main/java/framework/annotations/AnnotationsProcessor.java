package framework.annotations;

/*Guide
 * 1. Import Statements
 * 2. Class Fields
 * 3. Getters
 * 4. Setters
 * 5. Overridden
 * */

// --------------------------------------------------//
//                1.Import Statements                //
// --------------------------------------------------//
import framework.exceptions.NoSuchConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Class responsible for handling all processing related to annotations.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0
 */
public class AnnotationsProcessor implements IAnnotationsProcessor{

    // --------------------------------------------------//
    //                2.Class Fields                     //
    // --------------------------------------------------//
    private final Map<String, Class<?>> filesMappedToDataObject = new HashMap<>();
    private final Map<Class<?>, Class<?>[]> dataObjectMappedToPrimaryKeyTypes = new HashMap<>();
    private final List<DataObject> dataObjectsWithNoFiles = new ArrayList<>();

    public AnnotationsProcessor() {
        Set<Class<?>> clazzes = getAllClassesWith(DataObject.class);
        clazzes.iterator().forEachRemaining((clazz) -> {
            DataObject dataObject = clazz.getAnnotation(DataObject.class);
            Class<?>[] primaryTypeArr = dataObject.primitiveTypes();
            Class<?>[] primaryTypes = primaryTypeArr.length == 0 ? getPrimaryTypes(clazz) : primaryTypeArr;
        });
    }


    // --------------------------------------------------//
    //                   3.Getters                       //
    // --------------------------------------------------//
    /**
     * @param clazz &lt;? extends {@link Annotation}&gt;
     * @return Set&lt;Class&lt;?&gt;&gt;
     */
    protected final Set<Class<?>> getAllClassesWith(Class<? extends Annotation> clazz){
        Reflections reflections = new Reflections("");
        return reflections.getTypesAnnotatedWith(clazz);
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    private Class<?>[] getPrimaryTypes(@NotNull Class<?> clazz) {
        Class<?>[] res;
        if(clazz.isAnnotationPresent(DataObjectField.class)){
            res = new Class[0];
            //TODO: implement get primary types from field annotations
        }else{
            //TODO: Grab all fields and their types
            Field[] fields = clazz.getFields();
            res = new Class[fields.length];
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                res[i] = field.getType();
            }
        }
        return res;
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


    @Override
    public DataObject getDataObjectForFilename(String fileName) {
        return null;
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
        DataObject dataObject = null;
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

    private void setupPrimaryColumnsTypes(@NotNull DataObject dataObject) {
        if(){

        }
        System.out.println(dataObject.fileName());
        System.out.println(Arrays.toString(dataObject.primaryKeys()));
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
