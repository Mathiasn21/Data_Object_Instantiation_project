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
import framework.errors.NoMatchingDataObjectError;
import framework.errors.NoSuchConstructorError;
import framework.utilities.Parser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Class responsible for handling all processing related to annotations.
 * @author Mathias Walter Nilsen Github: Mathiasn21 @ https://github.com/Mathiasn21
 * @version 1.0.4
 */
public final class AnnotationsProcessor implements IAnnotationsProcessor {

    // --------------------------------------------------//
    //                2.Class Fields                     //
    // --------------------------------------------------//
    private final static Map<String, Class<?>> resourceMappedToDataObject = new HashMap<>();
    private final static Map<Class<?>, Constructor<?>> objectMappedToConstructor = new HashMap<>();
    private final static Map<Constructor<?>, Class<?>[]> constructorToPrimaryTypes = new HashMap<>();
    private final static List<Class<?>> dataObjectsWithNoResources = new ArrayList<>();
    private final static List<Class<?>> nameSpaceMappedToNameSpaces = new ArrayList<>();

    public AnnotationsProcessor() {
        if(!objectMappedToConstructor.isEmpty()){return;}

        Set<Class<?>> clazzes = getAllDataObjectClasses();
        clazzes.iterator().forEachRemaining((clazz) -> {

            Class<?>[] primaryTypes = getPrimaryTypes(clazz);
            Constructor<?> constructor = getCorrespondingConstructor(clazz.getConstructors(), primaryTypes);
            objectMappedToConstructor.put(clazz, constructor);
            constructorToPrimaryTypes.put(constructor, primaryTypes);

            dataObjectsWithNoResources.add(clazz);
        });
    }


    // --------------------------------------------------//
    //                   3.Private Getters               //
    // --------------------------------------------------//
    /**
     * @param primaryTypes p
     * @param constructors {@link Constructor}&lt;?&gt;[]
     * @return {@link Constructor}&lt;?&gt;
     */
    @NotNull
    private Constructor<?> getCorrespondingConstructor(@NotNull Constructor<?>[] constructors, @NotNull Class<?>[] primaryTypes) {
        Constructor<?> partialMatch = null;
        for (Constructor<?> constructor : constructors) {
            Class<?>[] params = constructor.getParameterTypes();
            int primaryTypeUniqueHashCode = Arrays.hashCode(primaryTypes);
            int primaryTypeHashCode = calcHashcodeFrom(primaryTypes);

            if (constructor.isAnnotationPresent(DataConstructor.class)) {
                return constructor;

            } else if (params.length == primaryTypes.length) {
                int paramHashCode = calcHashcodeFrom(params);
                int paramUniqueHashCOde = Arrays.hashCode(params);

                if (paramUniqueHashCOde == primaryTypeUniqueHashCode) { return constructor;
                }else if (paramHashCode == primaryTypeHashCode) { partialMatch = constructor; }
            }
        }
        if(partialMatch != null){
            return partialMatch;
        }
        throw new NoSuchConstructorError();
    }

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

            if (field.isAnnotationPresent(DataField.class)) { dataFields.add(type);
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

    /**
     * @param name {@link String}
     * @return {@link Class}&lt;?&gt;
     */
    @Override
    @Nullable
    public final Class<?> getClassFromObjectSample(@NotNull String name){
        return resourceMappedToDataObject.get(name);
    }


    // --------------------------------------------------//
    //                   4.Contract Methods              //
    // --------------------------------------------------//
    /**
     * @param listWithInitArgs {@link List}&lt;{@link Object}[]&gt;
     * @param name String
     * @return {@link ObjectInformation}
     */
    @SuppressWarnings("unchecked")//Only one possible type of constructor class
    @Override
    public final @NotNull ObjectInformation initializeDataObjects(@NotNull List<Object[]> listWithInitArgs, @NotNull String name)
            throws ReflectiveOperationException {

        List<Object> listOfDataObjects = new ArrayList<>();
        Class<?> clazz = resourceMappedToDataObject.containsKey(name) ?
                resourceMappedToDataObject.get(name) : getDataObjectWithoutFile(listWithInitArgs.get(0));

        Constructor<? extends DataObject> constructor = (Constructor<? extends DataObject>) objectMappedToConstructor.get(clazz);
        for (Object[] initArgs : listWithInitArgs) {
            listOfDataObjects.add(constructor.newInstance(initArgs));
        }
        return new ObjectInformation(constructorToPrimaryTypes.get(constructor), clazz, listOfDataObjects);
    }


    /**
     * @param objects {@link Object} ...objects
     * @return {@link Class}&lt;?&gt;
     */
    @Contract(pure = true)
    @Override
    @Nullable
    public final Class<?> getClassFromObjectSample(@NotNull Object... objects){
        return getDataObjectWithoutFile(objects);
    }


    /**
     * @param sample {@link Object}[]
     * @return {@link Class}&lt;?&gt;
     */
    @Nullable
    @Contract(pure = true)
    @SuppressWarnings("unchecked")//Only one possible type of constructor class
    private Class<?> getDataObjectWithoutFile(@NotNull Object[] sample) {
        Class<?>[] types = new Class[sample.length];
        int i = 0;
        while (i < sample.length) {
            types[i] = Parser.primitiveParseFromObjectClass(sample[i].getClass());
            i++;
        }

        int typesUniqueHashCode = Arrays.hashCode(types);
        int typesHashCode = calcHashcodeFrom(types);
        Class<?> partialMatch = null;

        for (Class<?> clazz : dataObjectsWithNoResources) {
            Constructor<? extends DataObject> constructor = (Constructor<? extends DataObject>) objectMappedToConstructor.get(clazz);
            Class<?>[] params = constructor.getParameterTypes();
            int paramsUniqueHashCode = Arrays.hashCode(params);
            int paramsHashCode = calcHashcodeFrom(params);

            if(params.length == types.length ){
                if (paramsUniqueHashCode == typesUniqueHashCode) { return clazz;
                }else if (paramsHashCode == typesHashCode) { partialMatch = clazz; }
            }
        }

        if(partialMatch != null){ return partialMatch; }
        throw new NoMatchingDataObjectError();
    }


    /**
     * @param classes {@link Class}&lt;?&gt;[]
     * @return int hashcode that does not care for permutations
     */
    private int calcHashcodeFrom(@NotNull Class<?>[] classes){
        int sum = 0;
        for(Class<?> clazz : classes){
            sum += clazz.hashCode() >>> 3;
        }
        return (sum << 1) + 1;
    }
}
