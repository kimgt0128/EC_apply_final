package ECService.demo.dto;


import ECService.demo.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
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
    ApplyResponse.builder()
            .id(apply.getId())
            .name(apply.getName())
            .major(apply.getMajor())
            .studentId(apply.getStudentId())
            .birth(apply.getBirth())
            .phoneNumber(apply.getPhoneNumber())
            .email(apply.getEmail())
            .state(apply.getState())
            .createTime(apply.getCreateTime())
            .question1(apply.getQuestion1())
            .question2(apply.getQuestion2())
            .question3(apply.getQuestion3())
            .build();
    }
}
