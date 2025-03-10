package ECService.demo.dto;


import ECService.demo.entity.Apply;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class ApplyResponse {
    private Long id;
    private String name;
    private String major;
    private String studentId;
    private String birth;
    private String phoneNumber;
    private String email;
    private String state;
    private LocalDateTime createTime;
    private String question1;
    private String question2;
    private String question3;




    // Apply 엔티티로부터 ApplyResponse를 생성하는 새로운 생성자 정의


    public ApplyResponse(Apply apply) {
        this.id = apply.getId();
        this.name = apply.getName();
        this.major = apply.getMajor();
        this.studentId = apply.getStudentId();
        this.birth = apply.getBirth();
        this.phoneNumber = apply.getPhoneNumber();
        this.email = apply.getEmail();
        this.state = apply.getState();
        this.createTime = apply.getCreateTime();
        this.question1 = apply.getQuestion1();
        this.question2 = apply.getQuestion2();
        this.question3 = apply.getQuestion3();
    }

}
