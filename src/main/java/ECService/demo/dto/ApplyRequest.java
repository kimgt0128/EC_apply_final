package ECService.demo.dto;

import ECService.demo.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ApplyRequest {

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
                .name(this.getName())
                .major(this.getMajor())
                .birth(this.getBirth())
                .phoneNumber(this.getPhoneNumber())
                .studentId(this.getStudentId())
                .email(this.getEmail())
                .question1(this.getQuestion1())
                .question2(this.getQuestion2())
                .question3(this.getQuestion3())
                .build();
    }
}
