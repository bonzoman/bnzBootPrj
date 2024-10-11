package java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter @Setter
@ToString
public class Person {
    private int no;
    private String firstName;
    private String lastName;
    private boolean isKorean;

}

