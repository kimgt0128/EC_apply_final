package ECService.demo.dto;

import ECService.demo.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor

public class ApplySimpleInfoResponse {
    private Long id;
    private String name;
    private String major;
    private String birth;
    private String phoneNumber;
    private String state;
    private LocalDateTime createTime;

    public ApplySimpleInfoResponse(Apply apply) {
        this.id = apply.getId();
        this.name = apply.getName();
        this.major = apply.getMajor();
        this.birth = apply.getBirth();
        this.phoneNumber = apply.getPhoneNumber();
        this.state = apply.getState();
        this.createTime = apply.getCreateTime();
    }

}
