package ECService.demo.dto;

import ECService.demo.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ApplyForm {

    private String name;
    private String major;
    private String studentId;
    private String birth;
    private String phoneNumber;
    private String email;
    private String question1;
    private String question2;
    private String question3;

    //Entity로 만들기 위한 함수
    public Apply toEntity() {
        return Apply.builder()
                .name(name)
                .major(major)
                .studentId(studentId)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .email(email)
                .question1(question1)
                .question2(question2)
                .question3(question3)
                .build();
    }
}
