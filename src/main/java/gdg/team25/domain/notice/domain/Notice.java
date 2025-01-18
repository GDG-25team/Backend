package gdg.team25.domain.notice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    private String id;

    private String title;

    private String type;

    private String content;

    private String phoneNumber;

    private String location;

    private String due_date;

    @Builder
    public Notice(String title, String type, String content, String phoneNumber, String location, String due_date) {
        this.title = title;
        this.type = type;
        this.content = content;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.due_date = due_date;
    }
}
