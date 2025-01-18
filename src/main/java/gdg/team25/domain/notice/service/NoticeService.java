package gdg.team25.domain.notice.service;

import gdg.team25.domain.notice.domain.Notice;
import gdg.team25.domain.notice.dto.NoticeResponse;
import gdg.team25.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public void init() {
        Notice notice = Notice.builder()
                .title("어린이교통안전지킴이")
                .type("교통/안전")
                .content("노인공익활동사업1. 활동시간 : 총 10회(일 3시간) / 월 30시간 활동2. 활동비 : 월 29만원3. 신청자격 : 65세 이상 기초연금수급자, 직역연금수급자(배우자 포함)    * 직역연금수급자(배우자 포함) 중 보건복지부 장관이 정하는 기준을 충족한 자는 참여 가능   - 65세 이상 대기자가 없는 경우 60-64세 차상위계층도 참여 가능4. 신청제외자 - 국민기초생활보장법에 의한 생계급여 수급자   (의료급여, 교육급여, 주거급여 수급자는 신청 가능)")
                .phoneNumber("02-459-5504")
                .location("서울시 강남구")
                .due_date("2025-09-30")
                .build();

        Notice notice2 = Notice.builder()
                .title("복지시설봉사단")
                .type("복지")
                .content("노인공익활동사업1. 활동시간 : 총 10회(일 3시간) / 월 30시간 활동2. 활동비 : 월 29만원3. 신청자격 : 65세 이상 기초연금수급자, 직역연금수급자(배우자 포함)    * 직역연금수급자(배우자 포함) 중 보건복지부 장관이 정하는 기준을 충족한 자는 참여 가능   - 65세 이상 대기자가 없는 경우 60-64세 차상위계층도 참여 가능4. 신청제외자 - 국민기초생활보장법에 의한 생계급여 수급자   (의료급여, 교육급여, 주거급여 수급자는 신청 가능)")
                .phoneNumber("02-459-5504")
                .location("서울시 강남구")
                .due_date("2025-09-30")
                .build();

        Notice notice3 = Notice.builder()
                .title("커피찌꺼기 새활용(새활용사업단)")
                .type("새활용")
                .content("노인공익활동사업1. 활동시간 : 총 10회(일 3시간) / 월 30시간 활동2. 활동비 : 월 29만원3. 신청자격 : 65세 이상 기초연금수급자, 직역연금수급자(배우자 포함)    * 직역연금수급자(배우자 포함) 중 보건복지부 장관이 정하는 기준을 충족한 자는 참여 가능   - 65세 이상 대기자가 없는 경우 60-64세 차상위계층도 참여 가능4. 신청제외자 - 국민기초생활보장법에 의한 생계급여 수급자   (의료급여, 교육급여, 주거급여 수급자는 신청 가능)")
                .phoneNumber("02-459-5504")
                .location("서울시 강남구")
                .due_date("2025-09-30")
                .build();

        noticeRepository.save(notice);
        noticeRepository.save(notice2);
        noticeRepository.save(notice3);
    }

    public List<NoticeResponse> getNoticeResponses() {
        List<Notice> noticeList = noticeRepository.findAll();
        return noticeList.stream()
                .map(NoticeResponse::from)
                .collect(Collectors.toList());
    }

    public Notice getNoticeById(String id) {
        return noticeRepository.findById(id);
    }
}
