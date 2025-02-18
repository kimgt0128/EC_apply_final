package ECService.demo.dto;

import ECService.demo.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyResultResponse {
    private String name;
    private String phoneNumber;
    private String state;

    // Apply 엔티티로부터 ApplyResultResponse를 생성하는 생성자
    public ApplyResultResponse(Apply apply) {
        this.name = apply.getName();
        this.phoneNumber = apply.getPhoneNumber();
        this.state = apply.getState();
    }


}
