package gdg.team25.domain.resume.dto.openApiDTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage extends Message {
    private String content;

    public TextMessage(String role, String content) {
        super(role);
        this.content = content;
    }
}