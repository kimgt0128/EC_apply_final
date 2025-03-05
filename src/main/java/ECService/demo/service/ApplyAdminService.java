package ECService.demo.service;

import ECService.demo.dto.ApplyResponse;
import ECService.demo.dto.ApplySimpleInfoResponse;
import ECService.demo.entity.Apply;
import ECService.demo.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j

@Service
public class ApplyAdminService {
    private final ApplyRepository applyRepository;


    public ApplyResponse getApply(Long id) {
        Apply apply = applyRepository.findById(id).orElse(null);
        return new ApplyResponse(apply);
    }

    public List<ApplySimpleInfoResponse> getAllApply() {
        List<Apply> applies = applyRepository.findAll();
        List<ApplySimpleInfoResponse> responses = new ArrayList<>();
        for (Apply apply : applies) {
            responses.add(new ApplySimpleInfoResponse(apply));
        }
        return responses;
    }

    public String update(Long id, String state) {
        Apply apply = applyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid apply Id: " + id));
        if (state.equals("pass")) {
            apply.pass();
        }
        if (state.equals("fail")) {
            apply.fail();
        }
        applyRepository.save(apply);
        String changed = apply.getState();
        //log.info("지원자의 상태가 {}으로 변경됨", changed);
        return changed;
    }
}
