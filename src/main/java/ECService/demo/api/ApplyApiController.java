package ECService.demo.api;

import ECService.demo.dto.ApplyForm;
import ECService.demo.entity.Apply;
import ECService.demo.repository.ApplyRepository;
import ECService.demo.repository.ListInfoRepository;
import ECService.demo.repository.mapping.ListInfoMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController //RestAPI 용 컨트롤러 - JSON을 반환
@Slf4j // 각 메서드마다 로그 남기기 -> 이후 유지보수
public class ApplyApiController {
    @Autowired
    ApplyRepository applyRepository;
    @Autowired
    ListInfoRepository listInfoRepository;

    //GET
    @GetMapping("/api/applies")
    public ResponseEntity<List<ListInfoMapping>> index() {
        List<ListInfoMapping> index = listInfoRepository.findAllBy();
//        for(ListInfoMapping mapping : index) {
//            String logMessage = String.format("ListInfoMapping: id=%d, name=%s, createTime=%s",
//                    mapping.getId(), mapping.getName(), mapping.getState(), mapping.getPhoneNumber(), mapping.getCreateTime());
//            log.info(logMessage);
//        }
        log.info("Fetched all ListInfoMappings, total count: {}", index.size());
        return ResponseEntity.ok(index);
    }

    @GetMapping("/api/applies/{id}")
    public ResponseEntity< Apply> showForm(@PathVariable Long id) {
        Apply formEntity = applyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid apply"));
        log.info("Fetched apply entity with ID: {}", id);
        return ResponseEntity.ok(formEntity);
    }

    ///POST
    @PostMapping("/api/applyForm")
    public ResponseEntity<Apply> applyForm(@RequestBody ApplyForm applyDto) {
        Apply applyEntity = applyDto.toEntity();
        Apply savedEntity = applyRepository.save(applyEntity);
        log.info("Saved Apply entity: {}", savedEntity);
        return ResponseEntity.ok(savedEntity);
    }

    @PostMapping("/api/showResult")
    public ResponseEntity<Apply> showResult(@RequestParam(required = false) String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            log.error("PhoneNumber is empty");
            throw new IllegalArgumentException("PhoneNumber is empty");
        }

        Apply applyEntity = applyRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NoSuchElementException("PhoneNumber not found"));
        log.info("Found application for phone number {}: {}", phoneNumber, applyEntity);
        return ResponseEntity.ok(applyEntity);
    }


    //PATCH
    //RequestBody를 통한 JSON 받기
    @PatchMapping("/api/apply/{id}/update")
    public ResponseEntity<Apply> updateState(@PathVariable Long id,
                             @RequestBody Map<String, String> requestBody) {
        String state = requestBody.get("state");
        Apply applyEntity = applyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
        applyEntity.setState(state);
        log.info("change state {}: {}", state, applyEntity);

        Apply updateEntity = applyRepository.save(applyEntity);
        return ResponseEntity.ok(updateEntity);
    }
    //RequestParam을 통하여 본문 내용에 직접 접근
    @PatchMapping("/api/apply{id}/update")
    public ResponseEntity<Apply> updateState1(@PathVariable Long id,
                              @RequestParam String state) {
        Apply applyEntity = applyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid apply"));
        applyEntity.setState(state);
        log.info("change state {}: {}", state, applyEntity);
        Apply updateEntity = applyRepository.save(applyEntity);
        return ResponseEntity.ok(updateEntity);
    }

    //DELETE
    @DeleteMapping("/api/deleteAll")
    public ResponseEntity<String> deleteAll() {
        if(!applyRepository.isExist()) {
            log.warn("applies is already empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 비어있습니다.");
        }
        applyRepository.deleteAll();
        applyRepository.resetAutoIncrement();
        log.info("successfully deleted all applies");
        return ResponseEntity.ok().build();
    }
}
