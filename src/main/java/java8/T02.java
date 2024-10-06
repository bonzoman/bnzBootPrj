package java8;

import java.util.function.Function;

public class T02 {
    public static void main(String[] args) {

        Function<Integer, Integer> plus10_old = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer i) {
                return i+10;
            }
        };
        System.out.println(plus10_old.apply(2));




        Function<Integer, Integer> plus10_new1 = (i) -> i+10;
        System.out.println(plus10_new1.apply(2));






    }
}
