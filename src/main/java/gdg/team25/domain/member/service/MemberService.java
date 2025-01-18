package gdg.team25.domain.member.service;

import gdg.team25.domain.member.domain.Member;
import gdg.team25.domain.member.dto.AddressRequest;
import gdg.team25.domain.member.dto.AddressResponse;
import gdg.team25.domain.member.dto.MemberResponse;
import gdg.team25.domain.member.repository.MemberRepository;
import gdg.team25.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public AddressResponse setAddress(AddressRequest addressRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Member member = memberRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(RuntimeException::new);

        member.setAddress(addressRequest.address());
        return AddressResponse.from(addressRequest.address());
    }

    public MemberResponse getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Member member = memberRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(RuntimeException::new);

        return MemberResponse.from(member.getId());
    }



}
