package gdg.team25.domain.notice.controller;

import gdg.team25.domain.notice.domain.Notice;
import gdg.team25.domain.notice.dto.NoticeResponse;
import gdg.team25.domain.notice.service.NoticeService;
import gdg.team25.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/init")
    @Operation(summary = "데이터 초기화", description = "데이터를 집어넣습니다.")
    public void init() {
        noticeService.init();
    }

    @GetMapping
    @Operation(summary = "공고 목록 조회", description = "공고 목록 조회합니다.")
    public BaseResponse<List<NoticeResponse>> getNotices() {
        return BaseResponse.ok(noticeService.getNoticeResponses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "공고 상세 조회", description = "공고 상세 조회합니다.")
    public BaseResponse<Notice> getNotices(@RequestParam String id) {
        return BaseResponse.ok(noticeService.getNoticeById(id));
    }
}
