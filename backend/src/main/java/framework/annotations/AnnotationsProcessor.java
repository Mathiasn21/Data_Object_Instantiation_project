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
            Class<?>[] primaryTypes = primaryTypeArr[0] == void.class ? getPrimaryTypes(clazz) : primaryTypeArr;
            dataObjectMappedToPrimaryKeyTypes.put(clazz, primaryTypes);

            String fileName = dataObject.fileName();
            if (fileName.equals("")) { dataObjectsWithNoFiles.add(dataObject);
            } else { filesMappedToDataObject.put(fileName, clazz); }
        });
    }


    // --------------------------------------------------//
    //                   3.Getters                       //
    // --------------------------------------------------//
    /**
     * @param clazz &lt;? extends {@link Annotation}&gt;
     * @return Set&lt;Class&lt;?&gt;&gt;
     */
    private Set<Class<?>> getAllClassesWith(Class<? extends Annotation> clazz){
        Reflections reflections = new Reflections("");
        return reflections.getTypesAnnotatedWith(clazz);
    }


    // --------------------------------------------------//
    //                   4.Private Getters               //
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

    @Override
    public DataObject getDataObjectForFilename(String fileName) {
        return null;
    }

    /**
     * @param listWithInitArgs d
     * @param file d
     * @return d
     * @throws InstantiationException d
     * @throws NoSuchMethodException d
     * @throws InvocationTargetException d
     * @throws IllegalAccessException d
     */
    @SuppressWarnings("unchecked")//Only one possible type extension
    @Override
    public List<Object> initializeDataObjectsFromFileName(@NotNull List<Object[]> listWithInitArgs, @NotNull String file) throws InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Object> listOfDataObjects = new ArrayList<>();
        Class<?> clazz = filesMappedToDataObject.get(file);

        Constructor<? extends DataObject> constructor = (Constructor<? extends DataObject>) clazz.getConstructor(dataObjectMappedToPrimaryKeyTypes.get(clazz));
        for (Object[] listWithInitArg : listWithInitArgs) {
            listOfDataObjects.add(constructor.newInstance(listWithInitArg));
        }
        return listOfDataObjects;
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
