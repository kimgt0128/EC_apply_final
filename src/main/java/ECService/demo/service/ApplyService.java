package ECService.demo.service;

import ECService.demo.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class ApplyService {

    private final ApplyRepository applyRepository;
}
