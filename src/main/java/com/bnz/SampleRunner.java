package com.bnz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * <pre>
 * [1.0] 프로그램 구동 후 Arguments 값 받아오기
 * [2.0] application.yml 값 받아오기
 * </pre>
 */
@Component
@Order(1) //ApplicationRunner 구현시 숫자 높은게 먼저 실행 됨
public class SampleRunner implements ApplicationRunner {

    @Autowired //추천
    SampleProperties properties;
    /*
    public SampleComponent(ApplicationArguments arguments) {
        //구동시 Vm 옵션(-Daaa=AAA)이 아닌 프로그램 Arguments(--bbb=BBB) 값을 읽어들임
        System.out.println("========ApplicationArguments test =======");
        System.out.println(arguments.getOptionNames());
    }
    */
    @Autowired
    DataSource dataSource;
    private Logger logger = LoggerFactory.getLogger(SampleRunner.class);
    //	[2.0] application.yml 값 받아오기(없으면 구동하다 error남 -- 비추)
    @Value("${kkk}")
    private String name;
    @Value("${randomvalue}")
    private String randomvalue;

//    @Autowired
//    JdbcTemplate jdbcTemplate;

    //[1.0] 프로그램 구동 후 Arguments 값 받아오기
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("==[info  logging]== properties.getFullName() : " + properties.getFullName());
        logger.debug("==[debug logging]== properties.getFullName() : " + properties.getFullName());


        //구동시 Vm 옵션(-Daaa=AAA)이 아닌 프로그램 Arguments(--ccc --ddd=DDD) 값을 읽어들임
        System.out.println("========ApplicationArguments(Run) 테스트 =======");
        System.out.println(args.getOptionNames());//[ccc, ddd]
        System.out.println(name + "-------" + randomvalue);
        System.out.println(properties.getName());
        System.out.println(properties.getAge());
        System.out.println(properties.getFullName());

        /* h2 in-memory db
         * spring.h2.console.enabled=true 설정시
         * http://localhost:8888/h2-console 접속 가능 */
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("conn.getMetaData().getURL() : " + conn.getMetaData().getURL());
            System.out.println("conn.getMetaData().getUserName() : " + conn.getMetaData().getUserName());
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate("CREATE TABLE USERS(ID VARCHAR(10) NOT NULL, NAME VARCHAR(100))");
        }

        //jdbcTemplate.execute("insert into USERS values('2', 'BONZO2')");


    }


}
