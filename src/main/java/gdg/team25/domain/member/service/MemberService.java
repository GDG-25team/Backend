package gdg.team25.domain.member.service;

import gdg.team25.domain.member.domain.Member;
import gdg.team25.domain.member.dto.*;
import gdg.team25.domain.member.dto.address.AddressRequest;
import gdg.team25.domain.member.dto.address.AddressResponse;
import gdg.team25.domain.member.dto.mypage.MyPageResponse;
import gdg.team25.domain.member.repository.MemberRepository;
import gdg.team25.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public MyPageResponse getPages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Member member = memberRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(RuntimeException::new);

        List<NoticeListResponse> noticeListResponseList = new ArrayList<>();

        NoticeListResponse noticeListResponse = NoticeListResponse.of(1L, "어린이교통안전지킴이");
        NoticeListResponse noticeListResponse2 = NoticeListResponse.of(2L, "복지시설봉사단");
        NoticeListResponse noticeListResponse3 = NoticeListResponse.of(3L, "커피찌꺼기 새활용");

        noticeListResponseList.add(noticeListResponse);
        noticeListResponseList.add(noticeListResponse2);
        noticeListResponseList.add(noticeListResponse3);

        return MyPageResponse.of(member.getName(), member.getAge(), noticeListResponseList);
    }

    public ResumeResponse getMyResume(Long id) {

        Carrer carrer = Carrer.of("어린이집", "보육교사", "2020년 3월부터 2020년 9월까지");
        List<Certificate> certificateList = new ArrayList<>();

        Certificate certificate = Certificate.of("어린이 보육 자격증", "2019년 6월");
        Certificate certificate1 = Certificate.of("보육 교사 1급", "2018년 10월");

        certificateList.add(certificate);
        certificateList.add(certificate1);

        String text = "\"안녕하세요, 저는 올해 68세로 평생 지역 식당을 운영하며 사람들과 함께 성장해 온 박순자입니다. 30년간 식당을 운영하며 단순히 음식을 제공하는 일을 넘어, 이웃과의 소통과 나눔의 가치를 배울 수 있었습니다.\n" +
                "\n" +
                "식당 운영을 통해 저는 성실함과 책임감을 기반으로 꾸준히 노력하는 자세를 익혔고, 지역 사회에서 많은 분들의 사랑을 받으며 서로를 돕는 공동체의 중요성을 깨달았습니다. 이러한 경험은 저를 사람들과의 협력을 즐기고, 맡은 일을 끝까지 책임지는 사람으로 성장시켰습니다.\n" +
                "\n" +
                "이제는 제가 배운 것들을 지역 사회에 환원하고 싶어 노인공익활동사업에 지원하게 되었습니다. 특히, 제가 가진 경험과 능력을 통해 작은 일이라도 누군가에게 도움이 되고, 지역 사회의 더 나은 내일을 만드는 데 일조하고 싶습니다. 새로운 환경에서 배우고 성장하며, 주변에 긍정적인 영향을 미치는 활동을 하고자 하는 저의 열정을 보여드리고 싶습니다.\n" +
                "\n" +
                "짧은 시간이지만, 맡겨주신 활동에 최선을 다해 지역 사회와 동료들에게 신뢰를 주는 참여자가 되겠습니다. 감사합니다.\"";

        return ResumeResponse.of("어린이 교통 안전 지킴이", carrer, certificateList, text);
    }

}
