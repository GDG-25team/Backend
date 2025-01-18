package gdg.team25.domain.notice.dto;

import gdg.team25.domain.notice.domain.Notice;
import jakarta.validation.constraints.NotBlank;

public record NoticeResponse(
        String id, @NotBlank String titleId, String title, String location
){
    public static NoticeResponse from(Notice notice) {
        return new NoticeResponse(notice.getId(), notice.getTitle(), notice.getTitle(), notice.getLocation());
    }
}
