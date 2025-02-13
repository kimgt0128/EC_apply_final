package ECService.demo.service;

import ECService.demo.dto.ApplyRequest;
import ECService.demo.dto.ApplyResponse;
import ECService.demo.dto.ApplyResultResponse;
import ECService.demo.entity.Apply;
import ECService.demo.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j

@Service
public class ApplyService {

    private final ApplyRepository applyRepository;

    public ApplyResponse save(ApplyRequest request) {
        Apply apply = request.toEntity();

        Apply saved = applyRepository.save(apply);

        log.info("Apply saved: {}", saved);

        return new ApplyResponse(saved);
    }

    public ApplyResultResponse result(String phonenumber) {
        Apply apply = applyRepository.findByPhoneNumber(phonenumber).orElse(null);
        log.info("Apply result: {}", apply);
        return new ApplyResultResponse(apply);
    }
}
