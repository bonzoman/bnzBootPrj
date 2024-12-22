package java8;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Record1Mapper {

    public static <T extends Record, R extends Record> R mapRecords(T src, Class<R> targetClass) {
        try {
            // Extract source record components (field names and values)
            Map<String, Object> sourceValues = new HashMap<>();
            for (RecordComponent srcComp : src.getClass().getRecordComponents()) {
                sourceValues.put(srcComp.getName(), srcComp.getAccessor().invoke(src));
            }

            // Prepare target record arguments
            RecordComponent[] targetComponents = targetClass.getRecordComponents();
            Object[] args = new Object[targetComponents.length];

            for (int i = 0; i < targetComponents.length; i++) {
                String targetField = targetComponents[i].getName();
                Object value = null;

                // Rule 1: Exact match
                if (sourceValues.containsKey(targetField)) {
                    value = sourceValues.get(targetField);
                } else {
                    // Rule 2: Remove "za_" prefix and check for a match
                    if (targetField.startsWith("za_")) {
                        String adjustedField = targetField.substring(3); // Remove "za_"
                        value = sourceValues.get(adjustedField);
                    }
                }

                // Set value (null if no match found)
                args[i] = value;
            }

            // Create target record using constructor
            return targetClass.getDeclaredConstructor(
                    Arrays.stream(targetComponents).map(RecordComponent::getType).toArray(Class[]::new)
            ).newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map records", e);
        }
    }

    public static void main(String[] args) {
        // Define source and target records
//        record A(String    aaa, String    bbb, String ccc) {}
//        record B(String za_aaa, String za_bbb, String ccc) {}
//        A source = new A("value1", "value2", "value3");

        record A(String    aaa, String    bbb, String ccc, String ddd           ) {}
        record B(String za_aaa, String za_bbb, String ccc,            String eee) {}
        record C(String    aaa, String    bbb, String ccc                       ) {}
        A a = new A("valueA", "valueB", "valueC", "valueD");


        // Map A to B
        B b = mapRecords(a, B.class);
        C c = mapRecords(a, C.class);

        // Print the result
        System.out.println(b);
        System.out.println(c);
    }
}
