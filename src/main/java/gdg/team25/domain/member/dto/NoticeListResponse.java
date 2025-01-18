package gdg.team25.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public record NoticeListResponse(
        @NotBlank Long noticeId,
        @NotBlank String title
)
{
    public static NoticeListResponse of(Long noticeId, String title) {
        return new NoticeListResponse(noticeId, title);
    }
}
