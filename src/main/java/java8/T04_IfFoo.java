package java8;

public interface T04_IfFoo {
    //desc 필수 구현해야 함
    void go();

    //desc 필수 구현하지 않아도 됨
    default void go2() {
        System.out.println("go2 (default IF)");
    }
}
