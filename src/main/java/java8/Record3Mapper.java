package java8;

import java.lang.reflect.RecordComponent;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Record3Mapper {

    // Cache for record components to minimize Reflection overhead
    private static final Map<Class<? extends Record>, RecordComponent[]> RECORD_COMPONENT_CACHE = new ConcurrentHashMap<>();

    // Public method to map a list of source records to a list of target records
    public static <T extends Record, R extends Record> List<R> mapRecordsList(List<T> sourceList, Class<R> targetClass) {
        if (sourceList == null) return Collections.emptyList();
        return sourceList.parallelStream()
                .filter(Objects::nonNull) // Ignore null objects in the source list
                .map(source -> mapRecord(source, targetClass))
                .collect(Collectors.toList());
    }

    // Method to map a single source record to a target record
    public static <T extends Record, R extends Record> R mapRecord(T source, Class<R> targetClass) {
        try {
            // Get or cache record components for source and target classes
            RecordComponent[] sourceComponents = getCachedRecordComponents(source.getClass());
            RecordComponent[] targetComponents = getCachedRecordComponents(targetClass);

            // Extract source values into a map
            Map<String, Object> sourceValues = Arrays.stream(sourceComponents)
                    .filter(component -> {
                        try{
                            return Objects.nonNull(component.getAccessor().invoke(source));
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to access source field: " + component.getName(), e);
                        }
                    })
                    .collect(Collectors.toMap(component -> {
                                String sourceField = component.getName();
                                if (sourceField.startsWith("za_") || sourceField.startsWith("ZA_")) {
                                    return sourceField.substring(3);
                                }
                                return sourceField;
                            },
                            component -> {
                                try {
                                    return component.getAccessor().invoke(source);
                                } catch (Exception e) {
                                    throw new RuntimeException("Failed to access source field: " + component.getName(), e);
                                }
                            }
//                            ,(existing, replacement) -> existing // Handle duplicate keys gracefully
                    ));


            // Prepare arguments for the target record
            Object[] args = Arrays.stream(targetComponents)
                    .map(targetComponent -> {
                        String targetField = targetComponent.getName();
                        Object value = null;

                        // Rule 1: Exact match
                        if (sourceValues.containsKey(targetField)) {
                            value = sourceValues.get(targetField);
                        } else if (targetField.startsWith("za_") || targetField.startsWith("ZA_")) {
                            String adjustedField = targetField.substring(3);
                            value = sourceValues.get(adjustedField);
                        }

                        // Rule 3: Handle type compatibility
                        if (value != null) {
                            value = handleTypeCompatibility(value, targetComponent.getType());
                        }

                        return value; // Return null if no match or incompatible type
                    })
                    .toArray();

            // Create target record using its canonical constructor
            return targetClass.getDeclaredConstructor(
                    Arrays.stream(targetComponents).map(RecordComponent::getType).toArray(Class[]::new)
            ).newInstance(args);

        } catch (Exception e) {
            throw new RuntimeException("Failed to map records", e);
        }
    }

    // Handle type compatibility for primitive and wrapper types
    private static Object handleTypeCompatibility(Object value, Class<?> targetType) {
        if (value == null) return null;

        if (targetType.isAssignableFrom(value.getClass())) {
            return value; // Directly assignable
        }

        // Handle specific type conversions
        if (targetType == Integer.class || targetType == int.class) {
            return ((Number) value).intValue();
        } else if (targetType == Long.class || targetType == long.class) {
            return ((Number) value).longValue();
        } else if (targetType == BigDecimal.class) {
            return new BigDecimal(value.toString());
        }

        return null; // Incompatible types are ignored
    }

    private static RecordComponent[] getCachedRecordComponents(Class<? extends Record> recordClass) {
        return RECORD_COMPONENT_CACHE.computeIfAbsent(recordClass, Class::getRecordComponents);
    }

    public static void main(String[] args) {
        // Define record types
        record A(String    aaa, String za_bbb, String ccc, Integer ddd,    Integer    eee, BigDecimal    fff) {}
        record B(String za_aaa, String    bbb,                Long ddd, BigDecimal za_eee,        int za_fff) {}

        // Sample data
        List<A> sourceList = List.of(
              new A("value1", "value2", "value3", 100 , 200,  new BigDecimal(30.0)),
              new A(null    , "value5", "null"  , 200 , 100,  new BigDecimal(90.2)),
              new A(null    , null    , "value6", null, null, new BigDecimal(30))
        );

        // Map List<A> to List<B>
        List<B> targetList = mapRecordsList(sourceList, B.class);

        // Print the result
        targetList.forEach(System.out::println);
    }
}

