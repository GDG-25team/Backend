package gdg.team25.domain.resume.dto.openApiDTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatGPTRequest {
    @JsonProperty("model")
    private String model;
    @JsonProperty("messages")
    private List<Message> messages;
    @JsonProperty("max_tokens")
    private int max_tokens;


    public static ChatGPTRequest createTextRequest(String model, int max_tokens, String role, String requestText, String fixedRequestText) {
        // List<String> 데이터를 하나의 문자열로 병합
        String combinedContent = String.join(" ", requestText, fixedRequestText);

        Message message = new TextMessage(role, combinedContent);
        return createChatGPTRequest(model, max_tokens, Collections.singletonList(message));
    }

    private static ChatGPTRequest createChatGPTRequest(String model, int max_tokens, List<Message> messages) {
        return ChatGPTRequest.builder()
                .model(model)
                .max_tokens(max_tokens)
                .messages(messages)
                .build();
    }

}