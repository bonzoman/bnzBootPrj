package java8;

import java.util.function.Function;

public class T02_Function {
    public static void main(String[] args) {

//        Function<Integer, Integer> plus10_old = new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer i) {
//                return i+10;
//            }
//        };
//        System.out.println(plus10_old.apply(2));

        //위 주석과 동일 code
        Function<Integer, Integer> plus10 = (i) -> i+10;
        System.out.println(plus10.apply(2));

        Function<Integer, Integer> mult20 = (i) -> i*2;
        System.out.println(mult20.apply(2));

        //desc compose : mult20실행 후 plus10실행
        Integer r1 = plus10.compose(mult20).apply(1);
        System.out.println(r1);

        //desc andThen : plus10실행 후 mult20실행
        Integer r2 = plus10.andThen(mult20).apply(1);
        System.out.println(r2);











    }
}
