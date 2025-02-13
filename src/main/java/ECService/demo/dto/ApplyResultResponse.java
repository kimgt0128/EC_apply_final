package ECService.demo.dto;

import ECService.demo.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyResultResponse {
    private String name;
    private String phoneNumber;
    private String state;

    // Apply 엔티티로부터 ApplyResultResponse를 생성하는 정적 팩토리 메서드
    public static ApplyResultResponse fromApply(Apply apply) {
        return new ApplyResultResponse(
                apply.getName(),
                apply.getPhoneNumber(),
                apply.getState()
        );
    }
}
