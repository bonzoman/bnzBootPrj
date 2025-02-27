package java8;

import java.util.function.*;

public class T02_Function {
    public static void main(String[] args) {

//        Function<Integer, Integer> plus10_old = new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer i) {
//                return i+10;
//            }
//        };
//        System.out.println(plus10_old.apply(2));

        //desc Function : 위 주석과 동일 code(in 1개, out 1개)
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        System.out.println(plus10.apply(2));

        //desc UnaryOperator : Function과 유사하나 in, out의 타입이 동일한 경우
        UnaryOperator<Integer> mult20 = (i) -> i * 2;
        System.out.println(mult20.apply(2));

        //NOTE: compose : mult20실행 후 plus10실행.
        Integer r1 = plus10.compose(mult20).apply(1);
        System.out.println(r1);

        //NOTE: andThen : plus10실행 후 mult20실행.
        Integer r2 = plus10.andThen(mult20).apply(1);
        System.out.println(r2);

        /////////////////////////////////////////////////////////////////

        //NOTE: Consumer : in 1개, out없음,          BiConsumer : in 2개, out없음
        Consumer<String> c1 = (in) -> System.out.println(in);
        c1.accept("나는 컨슈머1");

        //NOTE: Supplier : in없음, out만 존재
        Supplier<String> s1 = () -> "나는 서플라이서";
        System.out.println(s1.get());

        //NOTE: Predicate : in 1개, out boolean
        Predicate<String> p1 = (in) -> in.equals("a");
        System.out.println(p1.test("b"));


    }
}
