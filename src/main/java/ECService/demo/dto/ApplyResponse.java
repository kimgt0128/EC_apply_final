package ECService.demo.dto;


import ECService.demo.entity.Apply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyResponse {
    private Long id;  // 저장된 Apply 엔티티의 ID
    private String name;
    private String major;
    private String studentId;
    private String email;
    private String message;  // 처리 결과 메시지 (예: "저장되었습니다" 또는 오류 메시지)
    private boolean success;  // 처리 성공 여부

    // 생성자, getter, setter 등은 lombok 어노테이션으로 자동 생성됩니다.

    // Apply 엔티티로부터 ApplyResponse를 생성하는 정적 메서드
    public static ApplyResponse fromEntity(Apply apply, String message, boolean success) {
        return new ApplyResponse(
                apply.getId(),
                apply.getName(),
                apply.getMajor(),
                apply.getStudentId(),
                apply.getEmail(),
                message,
                success
        );
    }
}
