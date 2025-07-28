package java8;

import java.util.HashMap;
import java.util.Map;

public class T {
    public static void main(String[] args) {

// 예제 1: merge() 메서드를 사용하여 중복된 키의 값 병합
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "one");
        map1.put(2, "two");
        Map<Integer, String> map2 = new HashMap<>();
        map2.put(2, "deux");
        map2.put(3, "three");
        map2.forEach((key, value) -> map1.merge(key, value, (v1, v2) -> v1 + ", " + v2));
        System.out.println("Merged Map: " + map1);

        // 예제 2: put

//        All() 메서드를 사용하여 두 Map 합치기
        Map<Integer, String> map3 = new HashMap<>(map1);
        map3.putAll(map2);
        System.out.println("Merged Map with putAll(): " + map3);

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors (cores): " + cores);

    }
}
