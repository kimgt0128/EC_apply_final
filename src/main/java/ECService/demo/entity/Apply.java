package ECService.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "APPLY")
public class Apply {
    //ID -> 자동 증가하며 생성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String major;
    @Column
    private String studentId;
    @Column
    private String birth;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column(columnDefinition = "Text", length = 320)
    private String question1;
    @Column(columnDefinition = "Text", length = 320)
    private String question2;
    @Column(columnDefinition = "Text", length = 320)
    private String question3;

    //기본값 지정
    @Builder.Default
    @Column
    private String state = "unDefined"; //합격 여부를 확인할 변수 -> 이후 repository에서 update를 통해 상태 변화하기
    @Builder.Default
    @Column
    private LocalDateTime createTime = LocalDateTime.now();
}
