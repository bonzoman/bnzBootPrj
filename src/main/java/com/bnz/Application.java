package com.bnz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /** case 1111 */
//        SpringApplication.run(Application.class, args);

        /** case 2222 */
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);//default임
        app.addListeners(new SampleListener());////SpringContext 생성 전 발생하는 이벤트 리스너
        app.run(args);

        /** case 3333 커스터마이징 가능하게 */
//        SpringApplicationBuilder builder = new SpringApplicationBuilder();
//        builder.sources(Application.class).build();
//        SpringApplication app = builder.application();
//        app.addListeners(new SampleListener());//SpringContext 생성 전 발생하는 이벤트 리스너
//        app.run(args);

    }

    /* 추가 Connector 설정 ************************************ */
//    @Bean
//    public ServletWebServerFactory serverFactory() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(8080);
//        tomcat.addAdditionalTomcatConnectors(connector);
//        return tomcat;
//    }
    /* 추가 Connector 설정 ************************************ */

}
