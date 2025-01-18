package gdg.team25.domain.member.controller;

import gdg.team25.domain.member.dto.MemberResponse;
import gdg.team25.domain.member.dto.ResumeResponse;
import gdg.team25.domain.member.dto.mypage.MyPageResponse;
import gdg.team25.domain.member.service.MemberService;
import gdg.team25.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @Operation(summary = "사용자 정보 받아오기", description = "사용자 userId를 받아옵니다.")
    public BaseResponse<MemberResponse> getInfo() {
        return BaseResponse.ok(memberService.getCurrentMember());
    }

    @GetMapping("/mypage")
    @Operation(summary = "마이페이지 API", description = "마이페이지 API")
    public BaseResponse<MyPageResponse> getMypages() {
        return BaseResponse.ok(memberService.getPages());
    }

    @GetMapping("/myresumes/{id}")
    @Operation(summary = "마이페이지 상세 조회", description = "마이페이지 상세 조회 API")
    public BaseResponse<ResumeResponse> getMyResume(@PathVariable Long id) {
        return BaseResponse.ok(memberService.getMyResume(id));
    }

}
