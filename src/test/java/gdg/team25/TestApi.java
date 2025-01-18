package gdg.team25;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestApi {
    @Value("${openai.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    @Test
    public void testChatGptApi() throws Exception {
        // RestTemplate 초기화
        RestTemplate restTemplate = new RestTemplate();

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // 요청 바디 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Object[]{
                Map.of("role", "user", "content", "시니어일자리에 대해 알아?")
        });
        requestBody.put("max_tokens", 100);

        // HTTP 요청 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // API 호출
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // 응답 검증
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

        // 응답 내용 확인
        String responseBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);

        String content = rootNode
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        System.out.println("ChatGPT Response: " + content);

        // 응답 결과가 비어 있지 않은지 검증
        assertThat(content).isNotEmpty();
    }
}
