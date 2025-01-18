package gdg.team25.domain.member.controller;

import gdg.team25.domain.member.dto.address.AddressRequest;
import gdg.team25.domain.member.dto.address.AddressResponse;
import gdg.team25.domain.member.service.AuthService;
import gdg.team25.domain.member.service.MemberService;
import gdg.team25.global.response.BaseResponse;
import gdg.team25.global.security.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "카카오 로그인", description = "카카오 로그인")
    public BaseResponse<TokenResponse> redirect(@RequestParam String code) {
        TokenResponse oAuthSignInResponse = authService.login(code);
        return BaseResponse.ok(oAuthSignInResponse);
    }

    @PostMapping("/address")
    @Operation(summary = "주소 수정", description = "사용자 주소 수정")
    public BaseResponse<AddressResponse> setAddress(@RequestBody @Valid AddressRequest addressRequest) {
        return BaseResponse.ok(memberService.setAddress(addressRequest));
    }
}
