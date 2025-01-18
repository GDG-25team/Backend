package gdg.team25.domain.resume.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gdg.team25.domain.resume.dto.openApiDTO.request.ChatGPTRequest;
import gdg.team25.domain.resume.dto.openApiDTO.response.ChatGPTResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatService{

    private final ChatgptService chatgptService;
    @Value("${openai.model}")
    private String apiModel;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    private final RestTemplate template;

    public String getChatResponse(String prompt) {
        // ChatGPT 에게 질문을 던집니다.
        return chatgptService.sendMessage(prompt);
    }
    public String createResume(String text, String requestText) throws JsonProcessingException {
        ChatGPTRequest request = ChatGPTRequest.createTextRequest(apiModel, 300, "user", text, requestText);
        //ChatGPTResponse chatGPTResponse =  template.postForObject(apiUrl, request, ChatGPTResponse.class);
        //return chatGPTResponse.getChoices().get(0).getMessage().getContent();
        //return template.postForObject(apiUrl, request, ChatGPTResponse.class);
        // 응답 내용 확인
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAiApiKey);
        headers.set("Content-Type", "application/json");

        // 요청 바디 설정
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Object[]{
                Map.of("role", "user", "content", "앞에 있는 문장들을 기반으로 시니어 취업 이력서 자기소개서로 쓸 수 있도록 만들어줘. 노인복지를 위해 노인일자리를 제공하는 사업을 해. 30년간 쉼없이 식당을 운영해온 박순자 할머니가 이 사업에 지원할때 자기소개를 어떻게 하면 좋을까? \n" +
                        "노인공익활동사업1. 활동시간 : 총 10회(일 3시간) / 월 30시간 활동2. 활동비 : 월 29만원3. 신청자격 : 65세 이상 기초연금수급자, 직역연금수급자(배우자 포함)    * 직역연금수급자(배우자 포함) 중 보건복지부 장관이 정하는 기준을 충족한 자는 참여 가능   - 65세 이상 대기자가 없는 경우 60-64세 차상위계층도 참여 가능4. 신청제외자 - 국민기초생활보장법에 의한 생계급여 수급자   (의료급여, 교육급여, 주거급여 수급자는 신청 가능) - 국민건강보험 직장가입자 - 장기요양보험 등급 판정자 1~5등급, 인지지원등급   (인지지원등급자의 경우 전문의의 활동 가능 진단서 첨부 시에 한해 신청 가능) - 정부부처 및 지자체에서 추진하는 일자리사업에 2개 이상 참여하고 있는 자 - 국내 거주자 중 외국민인 자5. 참여자 선발 : 개별면담을 통해 ‘참여자 선발 기준표’ 작성하여 고득점자 순으로 통합 선발6. 준비서류 : 주민등록등본 1부, 기초연금수령확인서 1부, 통장사본 1부7. 신청장소 : 수서종합사회복지관 2 . 앞의 문장은 해당 취업 공고야. 그리고 100자 이내로 작성해줘.")
        });
        requestBody.put("max_tokens", 300);

        // HTTP 요청 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = template.exchange(
                apiUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );


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

        String answer = "ChatGPT Response: " + content;

        return answer;
    }
}
