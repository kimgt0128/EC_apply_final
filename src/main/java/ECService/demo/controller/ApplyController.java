package ECService.demo.controller;


import ECService.demo.dto.ApplyResponse;
import ECService.demo.dto.ApplySimpleInfoResponse;
import ECService.demo.entity.Apply;
import ECService.demo.service.ApplyAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j //로깅 어노테이션
@RequiredArgsConstructor

@Controller
public class ApplyController {


    private final ApplyAdminService applyAdminService;

    @GetMapping("/applies{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        //URL로부터 id를 받아서
        System.out.println("controller 실행 - id = " + id);

        ApplyResponse response = applyAdminService.getApply(id);

        log.info("response: {}", response);
        //2. 가져온 데이터를 model에 등록하기
        model.addAttribute("apply", response);
        //3. 보여줄 페이지를 설정하기
        return "apply/showApply";
    }

    //caddy에서 하위파일 지원 오류로 다시 만든 메서드
/*    @GetMapping("/applies{id}")
    public String showForm_copy(@PathVariable Long id, Model model) {
        //URL로부터 id를 받아서
        System.out.println("controller 실행 - id = " + id);
        //1. id로 데이터를 가져오기
        Apply applyEntity = applyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid apply"));
        //Optional<Apply> applyEntity = applyRepository.findById(id);
        log.info(applyEntity.toString());
        //2. 가져온 데이터를 model에 등록하기
        model.addAttribute("apply", applyEntity);
        //3. 보여줄 페이지를 설정하기
        return "apply/showApply";
    }*/

    @GetMapping("/applies")
    public String showApplyList(Model model) {
        //1. 모든 Apply를 가져온다.
        List<ApplySimpleInfoResponse> applies = applyAdminService.getAllApply();
        log.info("applies: {}", applies);
        //2. 가져온 Apply 묶음을 뷰로 전달
        model.addAttribute("applyList", applies);
        //3. 뷰 페이지를 설정
        return "apply/showList";
    }

    @PostMapping("apply{id}/update")
    public String updateState(@PathVariable Long id, @RequestParam String state, RedirectAttributes redirectAttributes) {
        //apply의 id와 status가져오기
        //System.out.println("지원자 id: " + id);
       //System.out.println("지원자 상태: " + state);
        log.info("지원자 id: " + id);
        log.info("전송된 상태: " + state);

        String changed = applyAdminService.update(id, state);

        //id로 DB에서 Entity를 찾고 setter로 바꾸기

        //변경 성공 메시지 전송
        redirectAttributes.addFlashAttribute("statusMessage", "지원자의 상태가 {changed}으로 변경되었습니다.");
        return "redirect:/applies";
    }

/*    @PostMapping("forms/lookUp")
    public String showResult(@RequestParam String phoneNumber, Model model) {
        log.info("Phone number: " + phoneNumber);
        //System.out.println("phoneNumber: " + phoneNumber);
        //phoneNumber로 apply를 찾을 수 없는 경우 예외처리하기!!!
        Apply applyEntity = applyRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 phoneNumber: " + phoneNumber));
        //System.out.println(applyEntity);
        //System.out.println(applyEntity.getState());
        log.info(applyEntity.toString());
        model.addAttribute("apply", applyEntity);
        return "apply/showResult";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(RedirectAttributes redirectAttributes) {
        if (!applyRepository.isExist()) {
            log.warn("applies is already empty");
            redirectAttributes.addFlashAttribute("message", "이미 비어있습니다.");
            return "redirect:/applies";
        }
        applyRepository.deleteAll();
        applyRepository.resetAutoIncrement();
        log.info("successfully deleted all applies");
        redirectAttributes.addFlashAttribute("message", "성공적으로 삭제되었습니다.");
        return "redirect:/applies";
    }*/

}
