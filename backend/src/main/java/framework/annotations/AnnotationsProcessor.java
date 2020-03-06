package framework.annotations;

/*Guide
 * 1. Import Statements
 * 2. Class Fields
 * 3. Private Getters
 * 4. Contract Methods
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

            Class<?>[] primaryTypes = getPrimaryTypes(clazz);
            dataObjectMappedToPrimaryKeyTypes.put(clazz, primaryTypes);

            String fileName = dataObject.fileName();
            if (fileName.equals("")) { dataObjectsWithNoFiles.add(dataObject);
            } else { filesMappedToDataObject.put(fileName, clazz); }
        });
    }


    // --------------------------------------------------//
    //                   3.Private Getters               //
    // --------------------------------------------------//
    /**
     * PrimaryTypes refers to the types that describes a dataset
     * @param clazz Class&lt;?&gt;&gt;
     * @return Class&lt;?&gt;&gt;[]
     */
    @NotNull
    @Contract(value = "_ -> new", pure = true)
    private Class<?>[] getPrimaryTypes(@NotNull Class<?> clazz) {
        Class<?>[] res;
        if(clazz.isAnnotationPresent(DataObjectField.class)){
            res = new Class[0];
            //TODO: implement get primary types from field annotations
        }else{
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
     * @param clazz &lt;? extends {@link Annotation}&gt;
     * @return Set&lt;Class&lt;?&gt;&gt;
     */
    private Set<Class<?>> getAllClassesWith(Class<? extends Annotation> clazz){
        Reflections reflections = new Reflections("");
        return reflections.getTypesAnnotatedWith(clazz);
    }

    /**
     * @param constructors Array
     * @param primaryKeys Array
     * @return Constructors
     */
    @NotNull
    private Constructor<?> getFirstMatchingConstructor(@NotNull Constructor<?>[] constructors, @NotNull Class<?>[] primaryKeys) {
        constructorLoop: for (Constructor<?> constructor : constructors) {
            Class<?>[] params = constructor.getParameterTypes();
            if(params.length != primaryKeys.length){
                continue;
            }

            for(int i = 0; i < params.length; i++){
                Class<?> param = params[i];
                if(param != primaryKeys[i]){
                    continue constructorLoop;
                }
            }
            return constructor;
        }
        throw new NoSuchConstructor();
    }


    // --------------------------------------------------//
    //                   4.Contract Methods              //
    // --------------------------------------------------//
    @Override
    public DataObject getDataObjectForFilename(String fileName) {
        //TODO implement logic for method
        return null;
    }


    /**
     * @param listWithInitArgs {@link List}&lt;{@link Object}[]&gt;
     * @param file String
     * @return {@link List}&lt;{@link Object}[]&gt;
     * @throws InstantiationException {@link InstantiationException} InstantiationException
     * @throws NoSuchMethodException {@link NoSuchMethodException} NoSuchMethodException
     * @throws InvocationTargetException {@link InvocationTargetException} InvocationTargetException
     * @throws IllegalAccessException {@link IllegalAccessException} IllegalAccessException
     */
    @SuppressWarnings("unchecked")//Only one possible type extension
    @Override
    public List<Object> initializeDataObjectsFromFileName(@NotNull List<Object[]> listWithInitArgs, @NotNull String file)
            throws InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<Object> listOfDataObjects = new ArrayList<>();
        Class<?> clazz = filesMappedToDataObject.get(file);

        Constructor<? extends DataObject> constructor = (Constructor<? extends DataObject>) clazz.getConstructor(dataObjectMappedToPrimaryKeyTypes.get(clazz));
        for (Object[] initArgs : listWithInitArgs) {
            listOfDataObjects.add(constructor.newInstance(initArgs));
        }
        return listOfDataObjects;
    }
}
