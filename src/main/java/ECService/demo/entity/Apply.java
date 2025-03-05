package ECService.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @Column(columnDefinition = "VARCHAR(255)")
    private String phoneNumber;
    @Column
    private String email;
    @Column(length = 500)
    private String question1;
    @Column(length = 500)
    private String question2;
    @Column(length = 500)
    private String question3;

    //기본값 지정
    //@ColumnDefault("'undefined'")
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'undefined'")
    private String state;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @PrePersist
    public void prePersist() {
        if (this.createTime == null) {
            this.createTime = LocalDateTime.now();
        }
        if (this.state == null) {
            this.state = "undefined";
        }
    }

    //합격 불합격
    public void pass() {
        this.state = "pass";
    }

    public void fail() {
        this.state = "fail";
    }
}
