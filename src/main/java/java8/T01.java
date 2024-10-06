package java8;

public class T01 {
    public static void main(String[] args) {

        //(구)익명 내부클래스 (anonymous inner class)
        FuncInterface fiOld = new FuncInterface() {
            @Override
            public void doIt(int num) {
                System.out.println(num+10);
            }
        };
        fiOld.doIt(1);


        //(신)익명 내부클래스 (anonymous inner class) -- 1줄인 경우
        FuncInterface fiNew1 = (num) ->  System.out.println(num+10);
        fiNew1.doIt(1);

        //(신)익명 내부클래스 (anonymous inner class) -- 2줄이상인 경우
        FuncInterface fiNew2 = (num) ->  {
            System.out.println(num+10);
            System.out.println(num+10);
        };
        fiNew2.doIt(1);







    }
}
