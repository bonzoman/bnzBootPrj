package java8;

import java8.vo.UserDto;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * stream이 처리하는 데이터 원본을 변경하지 않는다.!!!
 * stream.중개오퍼레이션 (stream을 리턴)
 *      filter, map, flatMap, distinct, sorted, peek, limit, skip
 * stream.종료오퍼레이션(stream을 리턴하지 않음)
 *      forEach, reduce, collect, min, max, anyMatch, allMatch, noneMatch, findFirst, sum, average
 */
@Slf4j
public class T06_Stream {
    public static void main(String[] args) {

        List<String> strList = new ArrayList<>(){{ add("Lokesh"); add("Alex"); add("Brian"); add("Neon"); add("David"); add("Alex"); add("Brian"); }};

        ArrayList<Person> person1List = new ArrayList<>();
        person1List.add( new Person(9, "Brian", "Suxena", false) );
        person1List.add( new Person(2, "siyoung", "oh", true) );
        person1List.add( new Person(4, "Brian", "Sux", false) );
        person1List.add( new Person(1, "Lokesh", "Gupta", false) );
        person1List.add( new Person(5, "Neon", "Piper", false) );
        person1List.add( new Person(6, "soojung", "park", true) );
        person1List.add( new Person(7, "soojung", "lee", true) );
        person1List.add( new Person(3, "doyoung", "oh", true) );
        person1List.add( new Person(8, "Alex", "Beckham", false) );

        ArrayList<Person> person2List = new ArrayList<>();
        person2List.add( new Person(1, "minyoung", "oh", true) );
        person2List.add( new Person(2, "jisoo", "kim", true) );

        Map<String, Object> resultMap1 = Map.of(
                "status", "success",
                "t_code", List.of(
                        Map.of("userName", "홍길동", "deptCd", "A101", "upDeptCd", "A100"),
                        Map.of("userName", "김상현", "deptCd", "A301", "upDeptCd", "A300"),
                        Map.of("userName", "최민호", "deptCd", "A201", "upDeptCd", "A200")
                )
        );


        Map<String, Object> resultMap2 = new HashMap<>(Map.of(
                "status", "success",
                "t_code", new ArrayList<Map<String, Object>>() {{
                    add(new HashMap<>(Map.of("userName", "홍길동", "deptCd", "A101", "upDeptCd", "A100")));
                    add(new HashMap<>(Map.of("userName", "김상현", "deptCd", "A301", "upDeptCd", "A300")));
                    add(new HashMap<>(Map.of("userName", "최민호", "deptCd", "A201", "upDeptCd", "A200")));
//                  add(new HashMap<>(){{put("userName", "홍길동"); put("deptCd", "A101"); put("upDeptCd", "A100");}});
//                  add(new HashMap<>(){{put("userName", "김상현"); put("deptCd", "A301"); put("upDeptCd", "A300");}});
//                  add(new HashMap<>(){{put("userName", "최민호"); put("deptCd", "A201"); put("upDeptCd", "A200");}});
                }}
        ));


        ///////////////////////////////////////////////////////////////////////////////////

        if(true) {
            //desc ((( stream 젼환 전 )))  List<Map> -> List<Dto>
            {
                List<UserDto> retrunDto = new ArrayList<>();
                List<Map> mapList = (List<Map>)resultMap1.get("t_code");
                for (Map map : mapList) {
                    retrunDto.add(
                            UserDto.builder()
                                    .userName(map.get("userName").toString())
                                    .deptCd(map.get("deptCd").toString())
                                    .upDeptCd(map.get("upDeptCd").toString())
                                    .build()
                    );
                }
                retrunDto.forEach(System.out::println);
                log.debug("end");
            }
            //desc ((( stream 젼환 후 )))  List<Map> -> List<Dto>
            {
                // Stream을 활용하여  List<UserDto>로 변환
                List<UserDto> retrunDto = ((List<Map<String, Object>>) resultMap1.get("t_code")).stream()
                        .map(map -> UserDto.builder()
                                .userName(map.get("userName").toString())
                                .deptCd  (map.get("deptCd"  ).toString())
                                .upDeptCd(map.get("upDeptCd").toString())
                                .build())
                        .collect(Collectors.toList());
                retrunDto.forEach(System.out::println);
                log.debug("end");
            }


            //desc ((( stream 젼환 전 )))  List<Map> -> (첫1건만) Dto
            {
                List<Map> mapList = (List<Map>)resultMap1.get("t_code");
                Map map = mapList.get(0);
                UserDto retrunDto = UserDto.builder()
                        .userName(map.get("userName").toString())
                        .deptCd  (map.get("deptCd"  ).toString())
                        .upDeptCd(map.get("upDeptCd").toString())
                        .build();
                System.out.println(retrunDto);
                log.debug("end");
            }
            //desc ((( stream 젼환 후 )))  List<Map> -> (첫1건만) Dto
            {
                // Stream을 활용하여 첫 번째 요소를 UserDto로 변환
                UserDto retrunDto = ((List<Map<String, Object>>) resultMap1.get("t_code")).stream()
                        .findFirst()
                        .map(map -> UserDto.builder()
                                .userName(map.get("userName").toString())
                                .deptCd  (map.get("deptCd"  ).toString())
                                .upDeptCd(map.get("upDeptCd").toString())
                                .build())
                        .orElse(null); // 요소가 없을 경우 null 반환
                System.out.println(retrunDto);
                log.debug("end");
            }

            log.trace("end");
        }

        if(false){
            //desc firstName 대문자로 변경
            Stream<String> sr1 = strList.stream().map(String::toUpperCase);
//            sr1.forEach(System.out::println);

            Stream<String> sr2 = person1List.stream().map(p -> p.getFirstName().toUpperCase());

//            sr2.collect(Collectors.toSet())
//            sr2.forEach(System.out::println);
        }

        if(false){
            System.out.println("====== firstName이 s로 시작하는 것만 필터");
            person1List.stream()
                    .filter(person -> person.getFirstName().startsWith("s"))
                    .forEach(person -> System.out.println(person.toString()));

            System.out.println("====== isKorean이 false인 것만 필터");
            person1List.stream()
                    .filter(Predicate.not(Person::isKorean))//method 참조방식
                    .forEach(person -> System.out.println(person.toString()));

            System.out.println("====== firstName만 모아서 스트림만들기");
            person1List.stream()
//                  .map(p -> p.getFirstName()) //아래 method 참조방식
                    .map(Person::getFirstName)
//                  .forEach(s -> System.out.println(s)); //아래 method 참조방식
                    .forEach(System.out::println);

            System.out.println("====== 두 person목록에 firstName+lastName 모으기");
            List<List<Person>> lll = new ArrayList<>();
            lll.add(person1List);
            lll.add(person2List);
            lll.stream()
//                  .flatMap(l -> l.stream()) //아래 method 참조방식
                    .flatMap(Collection::stream)
                    .forEach(p -> System.out.println(p.getFirstName() + "." + p.getLastName()));

            System.out.println("====== 100부터 1씩 증가하는 무제한 스트림에서 앞에 10개빼고 5개만");
            Stream.iterate(100, i -> i + 1)
                    .skip(10)
                    .limit(5)
                    .forEach(System.out::println);

            System.out.println("====== isKorean인 사람들의 이름만 List에 모으기");
            List<String> nList =  person1List.stream()
                    .filter(Person::isKorean)
                    .map(Person::getFirstName)
                    .toList();
            nList.forEach(System.out::println);

        }

        //stream match
        if(false) {

            System.out.println("====== lastName이 oh인 사람이 있는지 확인");
            //desc anyMatch : 최소 1개의 요소가 조건에 맞는지 체크
            boolean isExistOh = person1List.stream().anyMatch(p -> p.getLastName().contains("oh"));
            System.out.println(isExistOh);

            //desc allMatch : 모든 요소가 2의 배수인지 체크
            int [] intArr = {2,4,6};
            boolean isEven = Arrays.stream(intArr).allMatch(v -> v%2==0);
            System.out.println(isEven);

            //desc noneMatch : 모든 요소중 3의 배수가 없는지 체크
            boolean is3Multiple = Arrays.stream(intArr).noneMatch(v -> v%3==0);
            System.out.println(is3Multiple);
        }

        ///////////////////////////////////////////////////////////////////////////////////////
        //desc stream collect
        ///////////////////////////////////////////////////////////////////////////////////////
        if(false) {
            System.out.println("======1. Collectors.toSet");//set 순서 보장X
            Stream<String> fruits1 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
            Set<String> s1 = fruits1.collect(Collectors.toSet());
            s1.forEach(System.out::println);

            System.out.println("======2. Collectors.toList");//list 담긴순서대로
            Stream<String> fruits2 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
//          List<String> l1 = fruits2.collect(Collectors.toList()); //아래 동일
            List<String> l1 = fruits2.toList();
            l1.forEach(System.out::println);

            System.out.println("======3. 1개의 String으로 결합");
            Stream<String> fruits3 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
            String ss = fruits3.collect(Collectors.joining("-"));
            System.out.println(ss);

            System.out.println("======4. max 1개");
            Stream<String> fruits4 = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
//          Optional<String> result4 = fruits4.map(Object::toString).collect(maxBy(comparing(s -> s.length())));//아래와 동일
            Optional<String> result4 = fruits4.map(Object::toString).max(comparing(String::length));
            System.out.println("result4: " + result4.orElse("no item"));

            System.out.println("======51. map에 옮겨담기");
            ArrayList<Person> pList51 = new ArrayList<>();
            pList51.add( new Person(1, "a", "a", false) );
            pList51.add( new Person(2, "b", "b", false) );
            Map<Integer, String> map51 = pList51.stream().
                    collect(Collectors.toMap(Person::getNo, Person::getFirstName));
            map51.forEach((key, value) -> System.out.println(key + " : " + value));

            System.out.println("======52. map에 옮겨담기(toMap시 키중복)");
            ArrayList<Person> pList52 = new ArrayList<>();
            pList52.add( new Person(1, "a", "a", false) );
            pList52.add( new Person(1, "b", "b", false) );
            pList52.add( new Person(1, "c", "c", false) );

            //toMap 적용시 Key중복 발생 case(toMap 세번째 인자로 키중복시 어떤걸 담을지 선택처리)
            Map<Integer, String> map52 = pList52.stream().
                    collect(Collectors.toMap(Person::getNo //key
                                           , Person::getFirstName //value
                                           , (existValue, newValue) -> existValue) //중복발생시 기존값 유지
                    );
            map52.forEach((key, value) -> System.out.println(key + " : " + value));

            //toMap 적용시 Key중복 발생 case(toMap 세번째 인자로 키중복시 value합치기)
            map52 = pList52.stream().
                    collect(Collectors.toMap(Person::getNo //key
                                           , Person::getFirstName //value
                                           , (existValue, newValue) -> String.join(",",existValue, newValue))
                    );
            map52.forEach((key, value) -> System.out.println(key + " : " + value));

            //toMap 적용시 정열유지
            Map<Integer, String> map522 = pList52.stream().
                    collect(Collectors.toMap(Person::getNo //key
                                           , Person::getFirstName //value
                                           , (existValue, newValue) -> newValue //중복발생시 신규값으로 대체
                                           , LinkedHashMap::new) //LinkedHashMap 리턴으로 정열 유지!!!!!!!
                    );
            map522.forEach((key, value) -> System.out.println(key + " : " + value));

            System.out.println("======53. map에 옮겨담기(value에 Person객체 담기)");
            Map<Integer, Person> map53 = person1List.stream().
                    collect(Collectors.toMap(Person::getNo
                                           , Function.identity())//desc Function.identity() : value에 객체담기
                    );
            map53.forEach((key, person) -> System.out.println(key + " : " + person));

            System.out.println("======53-2. List에 옮겨담기");
            List<Person> plist53 = new ArrayList<>(map53.values());
            //System.out.println(plist53);
        }

        if(false){
            System.out.println("======6. person1List 정열");
            Comparator<Person> comparingRule;
//            comparingRule = Comparator.comparingInt(Person::getNo);            //no로 오름차순(asc)정열
//            comparingRule = Comparator.comparingInt(Person::getNo).reversed(); //no로 내림차순(desc)정열
//            comparingRule = Comparator.comparing(p -> p.getFirstName().toUpperCase()); //FirstName으로 대소문자구분없이 오름차순(asc)정열
            comparingRule = Comparator.comparing(Person::isKorean, Comparator.naturalOrder())
                                      .thenComparing(Person::getNo, Comparator.reverseOrder()); //isKorean 오름차순(asc)정열 & no 내림차순(desc)정열

            List<Person> plist6 = person1List.stream()
                                             .sorted(comparingRule)
                                             .toList();
            plist6.forEach(System.out::println);
        }




    }
}
