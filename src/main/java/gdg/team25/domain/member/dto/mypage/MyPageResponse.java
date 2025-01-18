package gdg.team25.domain.member.dto.mypage;

import gdg.team25.domain.member.dto.NoticeListResponse;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record MyPageResponse(
        @NotBlank String Name, @NotBlank Long age, List<NoticeListResponse> noticeListResponse
) {
    public static MyPageResponse of( String Name, Long age, List<NoticeListResponse> noticeListResponse) {
        return new MyPageResponse(Name, age, noticeListResponse);
    }
}
