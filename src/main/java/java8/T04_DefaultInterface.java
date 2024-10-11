package java8;

public class T04_DefaultInterface implements T04_IfFoo {

    @Override
    public void go(){
        System.out.println("go");
    }

//    @Override
//    public void go2(){
//        System.out.println("go2");
//    }

    public static void main(String[] args) {
        T04_DefaultInterface a = new T04_DefaultInterface();
        a.go2();


    }
}
