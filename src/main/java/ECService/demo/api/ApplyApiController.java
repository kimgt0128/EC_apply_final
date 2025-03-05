package ECService.demo.api;

import ECService.demo.dto.ApplyRequest;
import ECService.demo.dto.ApplyResponse;
import ECService.demo.dto.ApplyResultResponse;
import ECService.demo.entity.Apply;
import ECService.demo.repository.ApplyRepository;
import ECService.demo.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController //RestAPI 용 컨트롤러 - JSON을 반환
@Slf4j // 각 메서드마다 로그 남기기 -> 이후 유지보수
@RequiredArgsConstructor

@RequestMapping("/api")
public class ApplyApiController {
    @Autowired
    ApplyRepository applyRepository;


    private final ApplyService applyService;

//여기서부터

    // 새로운 지원자 추가
    @PostMapping("/apply")
    public ResponseEntity<ApplyResponse> createApplication(@RequestBody ApplyRequest request) {
        ApplyResponse response = applyService.save(request);

        log.info("response: {}", response);
        return response == null ?
                ResponseEntity.badRequest()
                        .build() :
                ResponseEntity.ok()
                        .body(response);
    }

    // 지원 결과 반환
    @GetMapping("/result")
    public ResponseEntity<ApplyResultResponse> showResult(@RequestParam String phoneNumber) {

        log.info("결과 요청 - phonenumber: {}", phoneNumber);
        ApplyResultResponse response = applyService.result(phoneNumber);

        log.info("상태 변경 완료 - response: {}", response);
        return response == null ?
                ResponseEntity.notFound()
                        .build() :
                ResponseEntity.ok()
                        .body(response);
    }
}
