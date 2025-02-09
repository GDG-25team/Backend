package gdg.team25.domain.resume.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gdg.team25.domain.resume.domain.Resume;
import gdg.team25.domain.resume.dto.openApiDTO.request.ChatGPTRequest;
import gdg.team25.domain.resume.dto.openApiDTO.response.ChatGPTResponse;
import gdg.team25.domain.resume.service.ChatService;
import gdg.team25.domain.resume.service.SpeechToTextService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
@Slf4j
public class SttRestController {
    private final SpeechToTextService sttService;
    private final ChatgptService chatgptService;
    private final ChatService chatService;

    @PostMapping(value = "/conversion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleAudioMessage(@RequestParam("audioFile") MultipartFile audioFile) throws IOException {
        String transcribe = sttService.transcribe(audioFile);
        return ResponseEntity.ok().body(transcribe);
    }
    @PostMapping("/chat-gpt/test")
    public String test(@RequestBody String question){
        return chatService.getChatResponse(question);

    }
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public Resume chat(@RequestBody String requestText) throws JsonProcessingException {

        return chatService.createResume(requestText);
    }
    @GetMapping("/carrer")
    public Resume carrer(@RequestBody String requestText) throws JsonProcessingException {

        return chatService.createCarrer(requestText);
    }

    @GetMapping("/certificates")
    public Resume certificates(@RequestBody String requestText) throws JsonProcessingException {

        return chatService.createCertificates(requestText);
    }

    @GetMapping("/{noticeId}")
    public Resume all(@PathVariable String noticeId) throws JsonProcessingException {

        return chatService.createSupport(noticeId);
    }



}
