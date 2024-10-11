package java8;

import java.util.*;
import java.util.stream.Collectors;

public class T05_Collection {
    public static void main(String[] args) {

        ///////////////////////////////////////////////////////////////////////////////////
        if(false){
            //desc Arrays.asList : 변경(set)만 가능
            //                     추가(add) X, 삭제(remove) X
            // @@@ Arrays.asList는 참조한 원본 배열의 값이 바뀌면 리스트의 값도 바뀌고, 반대로 List의 값을 수정해도 원본 배열의 값도 바뀌게 된다.
            String [] sArr = {"v3", "v2", "v1"};
            List<String> l1 = Arrays.asList(sArr);

            //desc List.of : 어떤 변경도 불가
            // @@@ List.of는 참조한 원본 배열의 값이 바뀌어도 리스트의 값은 바뀌지 않는다.
            List<String> l2 = List.of(sArr);

            System.out.println("원본 : " + l1.toString());
            System.out.println("원본 : " + l2.toString());
            l1.set(1,"mm2");//값 변경
            System.out.println("변경 : " + l1.toString());
            System.out.println("변경 : " + l2.toString());
        }
        ///////////////////////////////////////////////////////////////////////////////////


        if(false){
            List<String> l1 = new ArrayList<>(){{ add("a"); add("b"); add("c"); add("bcd"); }};

            //desc removeIf(Predicate<? super E> filter)
            boolean b = l1.removeIf(s -> s.startsWith("a"));//일부값 제거(filter)
            System.out.println(b);

            l1.forEach(System.out::println);
        }

        if(false){
            List<String> l1 = new ArrayList<>(){{ add("a"); add("b"); add("c"); add("bcd"); }};

            //desc stream
            //     map(Function<? super T, ? extends R> mapper)
            //     filter(Predicate<? super T> predicate)
            //     collect(Collector<? super T, A, R> collector)
            Set<String> sss = l1.stream().map(String::toUpperCase) //대문자로 변경
                    .filter(s -> s.startsWith("B")) //시작이 B인것만 필터
                    .collect(Collectors.toSet());

            System.out.println(sss.toString());
        }

        if(false){
            List<String> l1 = new ArrayList<>(){{ add("b"); add("c"); add("a"); add("bcd"); }};

            //desc sort(Comparator<? super E> c)
            Comparator<String> cToIgnoreCase = String::compareToIgnoreCase;
            l1.sort(cToIgnoreCase);//문자열 정열(ASC)
            System.out.println(l1.toString());

            l1.sort(cToIgnoreCase.reversed());//문자열 정열(DESC)
            System.out.println(l1.toString());
        }

        if(true){

            ArrayList<Person> personList = new ArrayList<>();
            personList.add( new Person(2, "Lokesh", "Gupta") );
            personList.add( new Person(1, "Alex", "Gussin") );
            personList.add( new Person(4, "Brian", "Sux") );
            personList.add( new Person(5, "Neon", "Piper") );
            personList.add( new Person(3, "David", "Beckham") );
            personList.add( new Person(7, "Alex", "Beckham") );
            personList.add( new Person(6, "Brian", "Suxena") );

            //desc FirstName정열
            Comparator<Person> compareByFirstName = Comparator.comparing( Person::getFirstName );

            //desc LastName정열
            Comparator<Person> compareByLastName = Comparator.comparing( Person::getLastName );

            //desc FirstName정열 후 LastName정열
            Comparator<Person> compareByFullName = compareByFirstName.thenComparing(compareByLastName);

            //desc 정열 수행
            Collections.sort(personList, compareByFullName);
            //personList.sort(compareByFullName); //위와동일(List.sort로 구현)
            personList.forEach(p -> System.out.println(p.getNo()+" "+p.getFirstName()+" "+p.getLastName() ));



            
            //desc FirstName정열 후 LastName정열 한방에...
            Comparator<Person> compareByName = Comparator
                    .comparing(Person::getFirstName)
                    .thenComparing(Person::getLastName);
            Collections.sort(personList, compareByName);
//            personList.sort(compareByName); //위와동일(List.sort로 구현)
            personList.forEach(p -> System.out.println(p.getNo()+" "+p.getFirstName()+" "+p.getLastName() ));
        }



    }
}
