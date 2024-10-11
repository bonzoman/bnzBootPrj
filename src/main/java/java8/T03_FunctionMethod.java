package java8;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class T03_FunctionMethod {
    public static void main(String[] args) {

        UnaryOperator<String> hi = Greeting::staticHi; // ::(콜론두개) Method Reference 표현방식(static)
        System.out.println(hi.apply("mom1"));

        UnaryOperator<String> hello = new Greeting()::hello; // ::(콜론두개) Method Reference 표현방식(none static)
        System.out.println(hello.apply("mom2"));


        Function<String, Greeting> f1 = Greeting::new;//생성자 Greeting(String name) 참조함
        System.out.println(f1.apply("mom3").getName());

        Supplier<Greeting> sG = Greeting::new;//생성자 Greeting() 참조함
        System.out.println(sG.get().getName());//아직 name에 값할당 안됨
        System.out.println(sG.get().hello("mom4"));


        //desc String배열 정열 tip(객체 레퍼런스)
        String [] strArr = {"c", "z", "a"};
        System.out.println(Arrays.toString(strArr));
        Arrays.sort(strArr, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(strArr));












    }
}
