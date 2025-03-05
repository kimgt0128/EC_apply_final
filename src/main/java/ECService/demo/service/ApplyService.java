package ECService.demo.service;

import ECService.demo.dto.ApplyRequest;
import ECService.demo.dto.ApplyResponse;
import ECService.demo.dto.ApplyResultResponse;
import ECService.demo.entity.Apply;
import ECService.demo.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ApplyResultResponse result(String phoneNumber) {
        ApplyResultResponse response = null;
        List<Apply> applies = applyRepository.findAll();
        //log.info("applies: {}", applies);
        String normalizedPhoneNumber = normalizePhoneNumber(phoneNumber); // Normalize the input phone number

        for (Apply apply : applies) {
            String normalizedApplyPhoneNumber = normalizePhoneNumber(apply.getPhoneNumber()); // Normalize the phone number from Apply entity

            //System.out.println(toHex(normalizedApplyPhoneNumber) + "|" + toHex(normalizedPhoneNumber)); // Print HEX to debug

            if (normalizedApplyPhoneNumber.equals(normalizedPhoneNumber)) {  // Compare normalized phone numbers
                log.info("[Service] - findByPhoneNumber 결과 - apply {}", apply);
                response = new ApplyResultResponse(apply);
            }
        }
        return response;
    }

    public ApplyResultResponse result1(String phoneNumber) {
        String hexPhoneNumber = toHex(phoneNumber);
        Apply apply = applyRepository.findByPhoneNumber(hexPhoneNumber).orElse(null);
        log.info("apply result: {}", apply);
        return new ApplyResultResponse(apply);
    }

    private static String toHex(String str) {
        StringBuilder hex = new StringBuilder();
        for (char c : str.toCharArray()) {
            hex.append(String.format("%04x", (int) c));
        }
        return hex.toString();
    }

    public String normalizePhoneNumber(String phoneNumber) {
        // Remove hyphens and spaces, trim any extra spaces
        return phoneNumber.replaceAll("[^\\d]", "").trim();
    }
}
