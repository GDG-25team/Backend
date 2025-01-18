package gdg.team25.domain.resume.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.w3c.dom.Text;

@Getter
@Setter
@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
    @Id
    private String id;

    private String userId;

    private String noticeId;

    private String carrer;

    private String others;

    private String intro;

    private boolean support;

    private boolean disabled;

    private boolean lowIncome;

    @Builder
    public Resume(String userId, String noticeId, String carrer, String others, String intro, boolean support, boolean disabled, boolean lowIncome) {
        this.userId = userId;
        this.noticeId = noticeId;
        this.carrer = carrer;
        this.others = others;
        this.intro = intro;
        this.support = support;
        this.disabled = disabled;
        this.lowIncome = lowIncome;
    }
}
