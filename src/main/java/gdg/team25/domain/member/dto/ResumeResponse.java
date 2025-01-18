package gdg.team25.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record ResumeResponse(
        @NotBlank String title, Carrer carrer, List<Certificate> certificate, String text)
{
    public static ResumeResponse of(String title, Carrer carrer, List<Certificate> certificate, String text) {
        return new ResumeResponse(title, carrer, certificate, text);
    }
}
