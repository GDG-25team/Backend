package gdg.team25.domain.member.service;

import gdg.team25.domain.member.domain.Member;
import gdg.team25.domain.member.dto.OAuth.KakaoProfile;
import gdg.team25.domain.member.dto.OAuth.OAuthToken;
import gdg.team25.domain.member.repository.MemberRepository;
import gdg.team25.global.security.JwtTokenProvider;
import gdg.team25.global.security.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.Charset;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private WebClient wc;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;


    public OAuthToken getToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:3000/login");
        params.add("client_id", "29b815bb1d6e2bfd10a26226a842d588");
        params.add("code", code);

        wc = WebClient.create("https://kauth.kakao.com/oauth/token");

        OAuthToken oauthTokenRes = wc.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .body(BodyInserters.fromFormData(params))
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(OAuthToken.class).block();

        return oauthTokenRes;
    }

    public TokenResponse login(String code) {
        OAuthToken oAuthToken = getToken(code);
        KakaoProfile kakaoProfile = getMemberInfo(oAuthToken.getAccessToken());

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        Long age = 30L;
        Member member = Member.builder()
                .email(generatedString)
                .age(21L)
                .gender("male")
                .phoneNumber("01039057347")
                .password(passwordEncoder.encode("1234"))
                .build();

        memberRepository.save(member);

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(generatedString, 1234));
        return jwtTokenProvider.createToken(authenticate);
    }

    public KakaoProfile getMemberInfo(String accessToken) {

        KakaoProfile profile = wc.mutate()
                .baseUrl("https://kapi.kakao.com")
                .build()
                .get()
                .uri("/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoProfile.class)
                .block();

        return profile;
    }
}
