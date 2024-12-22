package java8;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Record2Mapper {

    // Cache to store RecordComponent metadata for source and target classes
    private static final Map<Class<? extends Record>, RecordComponent[]> RECORD_COMPONENT_CACHE = new ConcurrentHashMap<>();

    public static <T extends Record, R extends Record> R mapRecords(T source, Class<R> targetClass) {
        try {
            // Get or cache source and target RecordComponents
            RecordComponent[] sourceComponents = RECORD_COMPONENT_CACHE.computeIfAbsent(
                    source.getClass(),
                    cls -> cls.getRecordComponents()
            );
            RecordComponent[] targetComponents = RECORD_COMPONENT_CACHE.computeIfAbsent(
                    targetClass,
                    cls -> cls.getRecordComponents()
            );

            // Extract source values into a map
            Map<String, Object> sourceValues = Arrays.stream(sourceComponents)
                    .collect(ConcurrentHashMap::new,
                            (map, component) -> {
                                try {
                                    map.put(component.getName(), component.getAccessor().invoke(source));
                                } catch (Exception e) {
                                    throw new RuntimeException("Failed to access source field: " + component.getName(), e);
                                }
                            },
                            ConcurrentHashMap::putAll
                    );

            // Prepare arguments for target record
            Object[] args = Arrays.stream(targetComponents)
                    .map(targetComponent -> {
                        String targetField = targetComponent.getName();
                        Object value = null;

                        // Rule 1: Exact match
                        if (sourceValues.containsKey(targetField)) {
                            value = sourceValues.get(targetField);
                        } else if (targetField.startsWith("za_")) { // Rule 2: Remove "za_" prefix and match
                            String adjustedField = targetField.substring(3);
                            value = sourceValues.get(adjustedField);
                        }

                        // Return value or null if no match found
                        return value;
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

    public static void main(String[] args) {
        // Define source and target records
        record A(String aaa, String bbb, String ccc) {}
        record B(String za_aaa, String za_bbb, String ccc) {}

        A source = new A("value1", "value2", "value3");

        // Map A to B
        B target = mapRecords(source, B.class);

        // Print the result
        System.out.println(target);
    }
}
