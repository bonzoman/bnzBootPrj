package java8;

import java.util.ArrayList;
import java.util.Optional;

public class T07_Optional {
    public static void main(String[] args) {

        if (true) {
            ArrayList<Person> person1List = new ArrayList<>();
            person1List.add(new Person(9, "Brian", "Suxena", false));
            person1List.add(new Person(2, "siyoung", "oh", true));
            person1List.add(new Person(4, "Brian", "Sux", false));
            person1List.add(new Person(1, "Lokesh", "Gupta", false));
            person1List.add(new Person(5, "Neon", "Piper", false));
            person1List.add(new Person(6, "soojung", "park", true));
            person1List.add(new Person(7, "soojung", "lee", true));
            person1List.add(new Person(3, "doyoung", "oh", true));
            person1List.add(new Person(8, "Alex", "Beckham", false));

            System.out.println("====== Optional");
            Optional<Person> person1 = person1List.stream()
                    .filter(p -> p.getFirstName().startsWith("B"))
                    .findFirst();

            Optional<Person> person2 = person1List.stream()
                    .filter(p -> p.getFirstName().startsWith("X"))
                    .findFirst();

            boolean isPerson = person1.isPresent();//값이 있으면 true
            System.out.println("====== isPresent : " + isPerson);

            boolean isEmpty = person1.isEmpty();//값이 없으면 true (java11부터)
            System.out.println("====== isEmpty : " + isEmpty);

            Person p1 = person1.get();//값 뽑기..없다면 NoSuchElementExceptoin발생
            System.out.println("====== get : " + p1.toString());

            System.out.println("====== 값이 있을때만 처리(리턴없음void)");
            person2.ifPresent(person -> {
                System.out.println("값이 있다.");
            });

            System.out.println("====== 값이 있으면 리턴하고 없으면 orElse의 구문(새로 생성) 수행");
            //NOTE: orElse의 구문은 값이 없더라도 실행은 된다.!!!(이거쓰지말고 orElseGet 써라)
            Person pp0 = person1.orElse(createNewPerson());
            System.out.println(pp0);

            System.out.println("====== 값이 있으면 리턴하고 없으면 orElseGet의 구문(새로 생성) 수행");
            //NOTE: orElseGet의 구문은 값이 없으면 실행안된다.!!!(이거써라)
            Person pp1 = person1.orElseGet(() -> createNewPerson());
            System.out.println(pp1);

            System.out.println("====== 값이 있으면 리턴하고 없으면 오류처리!!");
            Person pp2 = person2.orElseThrow(() -> new IllegalArgumentException("오류처리!!!"));
            System.out.println(pp2);

        }
    }

    private static Person createNewPerson() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>> createNewPerson method call");
        return new Person();
    }
}
