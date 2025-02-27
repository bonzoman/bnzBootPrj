package java8;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java8.vo.User1Dto;
import java8.vo.User2Dto;
import java8.vo.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
public class T09_Converter {

    static final PowerMapper powerMapper = Mappers.getMapper(PowerMapper.class);

    //List<Map>
    static final Map<String, Object> map01 = Map.of("userId", "1", "userName", "홍길동", "deptCd", "A101", "upDeptCd", "A100");

    //List<Map>
    static final List<Map<String, Object>> mapList01 = List.of(
            Map.of("userId", "1", "userName", "홍길동", "deptCd", "A101", "upDeptCd", "A100"),
            Map.of("userId", "2", "userName", "김상현", "deptCd", "A301", "upDeptCd", "A300"),
            Map.of("userId", "3", "userName", "최민호", "deptCd", "A201", "upDeptCd", "A200"));

    //Map<List<Map>>
//    static final Map<String, Object> listMap01 = Map.of(
//            "t_code", List.of(
//                    Map.of("userId","1","userName", "홍길동", "deptCd", "A101", "upDeptCd", "A100"),
//                    Map.of("userId","2","userName", "김상현", "deptCd", "A301", "upDeptCd", "A300"),
//                    Map.of("userId","3","userName", "최민호", "deptCd", "A201", "upDeptCd", "A200")));

    static final User1Dto user1Dto = User1Dto.builder().userId("1").userName("홍길동").deptCd("A101").upDeptCd("A100").build();

    static final List<User1Dto> user1DtoList = List.of(
            User1Dto.builder().userId("1").userName("홍길동").deptCd("A101").upDeptCd("A100").build(),
            User1Dto.builder().userId("2").userName("김상현").deptCd("A301").upDeptCd("A300").build(),
            User1Dto.builder().userId("3").userName("최민호").deptCd("A201").upDeptCd("A200").build()
    );

    public static void main(String[] args) {

        // List<Map> -> List<Dto>  -----  stream, ObjectMapper, mapstruct
        mapList_to_dtoList();

        //List<Dto1> -> List<Dto2>   stream, ObjectMapper, mapstruct
        dtoList_to_dtoList();

        //Map -> Dto ObjectMapper
        map_to_dto__byObjectMapper();
    }

    /**
     * ObjectMapper를 활용하여 Map -> Dto로 변환
     */
    private static void map_to_dto__byObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        UserDto userDto = mapper.convertValue(map01, UserDto.class);

        System.out.println(userDto);
    }

    /**
     * List<Map> -> List<Dto>로 변환
     */
    private static void mapList_to_dtoList() {
        //NOTE: stream 활용
        {
            List<UserDto> userDtoList = mapList01.stream()
                    .map(map -> UserDto.builder()
                            .userName(map.get("userName").toString()).deptCd(map.get("deptCd").toString()).upDeptCd(map.get("upDeptCd").toString())
                            .build())
                    .toList(); //collect(Collectors.toList());
            userDtoList.forEach(System.out::println);

            // Stream을 활용하여 첫 번째 요소를 UserDto로 변환
            UserDto userDto = mapList01.stream()
                    .findFirst()
                    .map(map -> UserDto.builder()
                            .userName(map.get("userName").toString()).deptCd(map.get("deptCd").toString()).upDeptCd(map.get("upDeptCd").toString())
                            .build())
                    .orElse(null); // 요소가 없을 경우 null 반환
            System.out.println(userDto);
        }
        //NOTE: ObjectMapper 활용
        {
            ObjectMapper mapper = new ObjectMapper();
            List<UserDto> userDtoList1 = mapper.convertValue(mapList01, mapper.getTypeFactory().constructCollectionType(List.class, UserDto.class));
            List<UserDto> userDtoList2 = mapper.convertValue(mapList01, new TypeReference<List<UserDto>>() {
            });

            userDtoList1.forEach(System.out::println);
            userDtoList2.forEach(System.out::println);
        }

        //NOTE: mapstruct 활용
        {
            //안해
        }

    }


    /**
     * mapstruct를 활용하여 List<Dto1> -> List<Dto2>로 변환
     */
    private static void dtoList_to_dtoList() {
        //NOTE: stream 활용
        {
            List<User2Dto> user2DtoList = user1DtoList.stream()
                    .map(user1Dto -> User2Dto.builder().userName(user1Dto.userName()).deptCd(user1Dto.deptCd()).upDeptCd(user1Dto.upDeptCd()).build())
                    .toList(); //collect(Collectors.toList());
            user2DtoList.forEach(System.out::println);
        }
        //NOTE: ObjectMapper 활용
        {
            ObjectMapper mapper = new ObjectMapper();
            List<User2Dto> user2DtoList = mapper.convertValue(user1DtoList, mapper.getTypeFactory().constructCollectionType(List.class, User2Dto.class));
            user2DtoList.forEach(System.out::println);
        }
        //NOTE: mapstruct 활용
        {
            List<User2Dto> user2DtoList = powerMapper.user1DtoList_to_user2DtoList(user1DtoList);
            System.out.println(user2DtoList);
        }

    }

//    /**
//     * mapstruct를 활용하여 Map -> Dto로 변환
//     */
//    private static void map_to_dto__byMapstruct() {
//        UserDto userDto = powerMapper.map_to_userDto(map01);
//    }


    /**
     * 편의상 inner class로 ...
     */
    @MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE
            , unmappedSourcePolicy = ReportingPolicy.IGNORE)
    public interface UnmappedIgnoreConfig {
    }


    /**
     * 편의상 inner class로 ...
     */
    @Mapper(componentModel = "spring"
//별도분리   , unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE
            , config = UnmappedIgnoreConfig.class, imports = LocalDateTime.class
            , mappingControl = DeepClone.class
    )
    public interface PowerMapper {
//        PowerMapper INSTANCE = Mappers.getMapper( PowerMapper.class );

        @Mapping(target = "userId", ignore = true)
        @Mapping(target = "userName", expression = "java(\"Jaws\")") //user2Dto.userName( "Jaws" );
        @Mapping(target = "jsonData", expression = "java(source.bbb())")
            //user2Dto.jsonData( source.getJsonData() );
        User2Dto user1Dto_to_user2Dto(User1Dto source);

        @Named("u1u2")
        User2Dto aaaaaaaaa(User1Dto source2);

        @IterableMapping(qualifiedByName = "u1u2")
            //list 돌때 사용할 메소드를 @Named로 지정
        List<User2Dto> user1DtoList_to_user2DtoList(List<User1Dto> source);

//        UserDto map_to_userDto(Map<String, Object> map);

        //Mapper에서 구현해야 할게 있다면~
        default UserDto map_to_userDto22222222222(Map<String, Object> map) {
            return null;
        }
    }

}
