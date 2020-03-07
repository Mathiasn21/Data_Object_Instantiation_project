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
import framework.errors.NoSuchConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

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
    private final Map<Class<?>, Constructor<?>> objectMappedToConstructor = new HashMap<>();
    private final List<DataObject> dataObjectsWithNoFiles = new ArrayList<>();

    public AnnotationsProcessor() {
        Set<Class<?>> clazzes = getAllDataObjectClasses();
        clazzes.iterator().forEachRemaining((clazz) -> {
            DataObject dataObject = clazz.getAnnotation(DataObject.class);

            Class<?>[] primaryTypes = getPrimaryTypes(clazz);
            dataObjectMappedToPrimaryKeyTypes.put(clazz, primaryTypes);
            objectMappedToConstructor.put(clazz, getCorrespondingConstructor(clazz.getConstructors(), primaryTypes));

            String fileName = dataObject.fileName();
            if (fileName.equals("")) { dataObjectsWithNoFiles.add(dataObject);
            } else { filesMappedToDataObject.put(fileName, clazz); }
        });
    }

    /**
     * @param constructors {@link Constructor}&lt;?&gt;[]
     * @return {@link Constructor}&lt;?&gt;
     */
    @NotNull
    private Constructor<?> getCorrespondingConstructor(@NotNull Constructor<?>[] constructors, @NotNull Class<?>[] primaryTypes) {
        constructorLoop: for(Constructor<?> constructor : constructors){
            Class<?>[] params = constructor.getParameterTypes();

            if(constructor.isAnnotationPresent(DataConstructor.class)){
                return constructor;
            }else if(params.length == primaryTypes.length){
                for(int i = 0; i < params.length; i++){
                    Class<?> param = params[i];
                    if(param != primaryTypes[i]){
                        continue constructorLoop;
                    }
                }
                return constructor;
            }
        }
        throw new NoSuchConstructor();
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
        List<Class<?>> dataFields = new ArrayList<>();//is annotated with DataField
        List<Class<?>> listOfFields = new ArrayList<>();//Is not annotated with DataField

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            Class<?> type = field.getType();

            if (field.isAnnotationPresent(DataObjectField.class)) { dataFields.add(type);
            } else { listOfFields.add(type); }
        }
        return (dataFields.isEmpty() ? listOfFields : dataFields).toArray(new Class[0]);
    }


    /**
     * @return Set&lt;Class&lt;?&gt;&gt;
     */
    private Set<Class<?>> getAllDataObjectClasses(){
        Reflections reflections = new Reflections("");
        return reflections.getTypesAnnotatedWith(DataObject.class);
    }


    // --------------------------------------------------//
    //                   4.Contract Methods              //
    // --------------------------------------------------//

    /**
     * @param listWithInitArgs {@link List}&lt;{@link Object}[]&gt;
     * @param file String
     * @return {@link List}&lt;{@link Object}[]&gt;
     * @throws InstantiationException {@link InstantiationException} InstantiationException
     * @throws InvocationTargetException {@link InvocationTargetException} InvocationTargetException
     * @throws IllegalAccessException {@link IllegalAccessException} IllegalAccessException
     */
    @SuppressWarnings("unchecked")//Only one possible type extension
    @Override
    public List<Object> initializeDataObjectsFromFileName(@NotNull List<Object[]> listWithInitArgs, @NotNull String file)
            throws InstantiationException, InvocationTargetException, IllegalAccessException {

        /*TODO: implement logic for whenever there is no mapped file.
         * It will then just find first matching class where the field types match
         * the required types.
         */
        List<Object> listOfDataObjects = new ArrayList<>();
        Class<?> clazz = filesMappedToDataObject.get(file);

        Constructor<? extends DataObject> constructor = (Constructor<? extends DataObject>) objectMappedToConstructor.get(clazz);
        for (Object[] initArgs : listWithInitArgs) {
            listOfDataObjects.add(constructor.newInstance(initArgs));
        }
        return listOfDataObjects;
    }
}
