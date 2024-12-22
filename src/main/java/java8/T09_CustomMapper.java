package java8;

import java8.vo.SourceDto;
import java8.vo.TargetDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Deprecated //record는 못씀
public class T09_CustomMapper {

    @SneakyThrows
    public static void main(String[] args) {

        SourceDto sourceDto = SourceDto.builder().userId("1").userName("홍길동").deptCd("A001").upDeptCd("A101").build();

        TargetDto targetDto = map_KKKKKKKKKKKKKKKKKKKK(sourceDto, TargetDto.class);
        System.out.println(targetDto);
    }

    public static <O, Target> O map_KKKKKKKKKKKKKKKKKKKK(Object srcObj, Class<Target> targetClass) {
        if (srcObj.getClass().isPrimitive() || targetClass.isPrimitive()) {
            throw new RuntimeException("Provide a non primitive class");
        }

        if (srcObj.getClass().isInterface() || targetClass.isInterface()) {
            throw new RuntimeException("Provide a non interface class");
        }

        if (srcObj.getClass().isEnum() || targetClass.isEnum()) {
            throw new RuntimeException("Provide a non enum class");
        }

        if (Modifier.isAbstract(srcObj.getClass().getModifiers()) || Modifier.isAbstract(targetClass.getModifiers())) {
            throw new RuntimeException("Provide a non abstract class");
        }

        return doMap_XXXXXXXXXXXXXXXXXXXXXX(srcObj, srcObj.getClass(), targetClass);
    }


    @SneakyThrows
    private static <O, Source, Target> O doMap_XXXXXXXXXXXXXXXXXXXXXX(Object sourceInstance, Class<Source> sourceClass, Class<Target> targetClass)  {
        Set<Field> declaredSourceFields = findAllFields_AAAAAAAAAAAAAAA(sourceClass);
        Set<Field> declaredTargetFields = findAllFields_AAAAAAAAAAAAAAA(targetClass);

        //record 는 여기서 오류남!
        Object targetInstance = targetClass.getDeclaredConstructor().newInstance();

        for (Field field : declaredTargetFields) {

            Field sourceField = findSourceField_QQQQQQQQQQQQQQQQQQQQQ(field, declaredSourceFields);
            if (sourceField != null) {
                field.setAccessible(true);
                field.set(targetInstance, sourceField.get(sourceInstance));
            }
        }
        return (O) targetInstance;
    }

    public static Set<Field> findAllFields_AAAAAAAAAAAAAAA(Class<?> inputClass) {
        Set<Field> allFields = new HashSet<>();

        if (inputClass == null) {
            return allFields;
        }

        Field[] fields = inputClass.getDeclaredFields();
        allFields.addAll(Set.of(fields));
        allFields.addAll(findAllFields_AAAAAAAAAAAAAAA(inputClass.getSuperclass()));
        return allFields;
    }

    private static Field findSourceField_QQQQQQQQQQQQQQQQQQQQQ(Field targetField, Set<Field> declaredSourceFields) {
        for (Field field : declaredSourceFields) {
            field.setAccessible(true);
            String sourceFieldName = field.getName();
            if (sourceFieldName.equals(targetField.getName()) &&
                    field.getType().equals(targetField.getType())) {
                return field;
            }
        }
        return null;
    }


}
