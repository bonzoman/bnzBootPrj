package java8;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class T08_Date {
    public static void main(String[] args) {
        //old
        Date date = new Date();
        long time = date.getTime();
        //java8
        System.out.println("========Instant");
        Instant instant = Instant.now();//UTC
        System.out.println(instant.toString());
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime.toString());

        System.out.println("========LocalDateTime(now)");
        LocalDateTime now = LocalDateTime.now();//UTC가 아님
        System.out.println(now.toLocalDate() + "---" + now.toLocalTime());

        System.out.println("========LocalDateTime(특정일)");
        LocalDateTime birthDay = LocalDateTime.of(1979, Month.APRIL,11,23,59,59);
        System.out.println(birthDay.toString());

        System.out.println("========두날짜비교");
        LocalDate today     = LocalDate.now();
        LocalDate birthDate = LocalDate.of(1979, Month.APRIL,11);
        Period period = Period.between(today, birthDate);
        System.out.println(period.getDays());

        Calendar calendar = new GregorianCalendar();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();



    }

}
