package java8;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CopyUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 기본 ObjectMapper 설정
        objectMapper.setPropertyNamingStrategy(new CustomNamingStrategy());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T copy(Object source, Class<T> targetClass) {
        try {
            Map<String, Object> transformed;
            if (source instanceof Map) {
                transformed = transformKeys((Map<String, Object>) source);
            } else {
                transformed = objectMapper.convertValue(source, Map.class);
            }

            return objectMapper.convertValue(transformed, targetClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to copy single object", e);
        }
    }

    public static <T> List<T> copy(List<?> sourceList, Class<T> targetClass) {
        try {
            // 데이터 키 변환
            List transformedList = sourceList.stream()
                    .map(item -> {
                        if (item instanceof Map) {
                            return transformKeys((Map<String, Object>) item);
                        } else {
                            return objectMapper.convertValue(item, Map.class);
                        }
                    })
                    .collect(Collectors.toList());

            // 변환된 데이터로 매핑
            return objectMapper.convertValue(transformedList,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, targetClass));
        } catch (Exception e) {
            throw new RuntimeException("Failed to copy list to target class: " + targetClass.getSimpleName(), e);
        }
    }

    private static Map<String, Object> transformKeys(Map<String, Object> sourceMap) {
        return sourceMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> normalizeKey(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    private static String normalizeKey(String key) {
        if (key.toLowerCase().startsWith("za_")) {
            key = key.substring(3); // "za_" 제거
        }
        return key.replace("_", "").toLowerCase(); // "_ 제거 및 소문자 변환
    }



    // 사용자 지정 Naming 전략
    private static class CustomNamingStrategy extends PropertyNamingStrategies.NamingBase {
        @Override
        public String translate(String propertyName) {
            return normalizeKey(propertyName);
        }
    }

    public static void main(String[] args) {
        //List
//        map_2_record();
//        map_2_dto();
//        record_2_class();
//        class_2_record();
        //단건
//        map_1_record();
//        map_1_dto();
        record_1_class();
//        class_1_record();

    }

    private static void class_2_record() {
        List<C1> c1List = List.of(new C1("aaaa","bbbbb","cccccc"));
        record R1(String za_no, String ZA_FIRST_NAME, String lastName) {}
        List<R1> r1List = copy(c1List, R1.class);
        System.out.println(r1List);
    }

    private static void record_2_class() {
        record R1(String za_no, String ZA_FIRST_NAME) {}
        List<R1> r1List = List.of(new R1("asdf","asdfsadf"));

        List<C2> c2List = copy(r1List, C2.class);
        System.out.println(c2List);


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class C1 { String no; String firstName; String za_lastName; }

    @Data
    public static class C2 { String no; String firstName; String za_lastName; }

    private static void map_2_dto() {
        // Map -> Record
        List<Map<String, Object>> m1 = List.of(
                Map.of("ZA_NO", "asdf", "firstName", "q3rgwe", "lastName", "afhweh")
        );

        // 변환 및 출력
        List<C1> c1List = copy(m1, C1.class);
        System.out.println(c1List);
    }

    private static void map_2_record() {
        // Map -> Record
        List<Map<String, Object>> m1 = List.of(
                Map.of("ZA_AAA_BBB", "value1", "za_bbb", "2a")
        );

        record R1(String aaaBbb, String BBB) {}

        // 변환 및 출력
        List<R1> r1List = copy(m1, R1.class);
        r1List.forEach(System.out::println);
    }










    private static void class_1_record() {
        C1 c1 = new C1("aaaa","bbbbb","cccccc");
        record R1(String za_no, String ZA_FIRST_NAME, String lastName) {}
        R1 r1 = copy(c1, R1.class);
        System.out.println(r1);
    }

    private static void record_1_class() {
        record R1(String za_no, String ZA_FIRST_NAME) {}
        R1 r1 = new R1("asdf","asdfsadf");

        C1 c1 = copy(r1, C1.class);
        System.out.println(c1);
    }

    private static void map_1_dto() {
        // Map -> Record
        List<Map<String, Object>> m1 = List.of(
                Map.of("ZA_NO", "asdf", "firstName", "q3rgwe", "lastName", "afhweh")
        );

        // 변환 및 출력
        List<C1> c1List = copy(m1, C1.class);
        System.out.println(c1List);
    }

    private static void map_1_record() {
        // Map -> Record
        List<Map<String, Object>> m1 = List.of(
                Map.of("ZA_AAA_BBB", "value1", "za_bbb", "2a")
        );

        record R1(String aaaBbb, String BBB) {}

        // 변환 및 출력
        List<R1> r1List = copy(m1, R1.class);
        r1List.forEach(System.out::println);
    }

}
