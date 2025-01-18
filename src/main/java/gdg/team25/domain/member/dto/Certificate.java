package gdg.team25.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public record Certificate(
        @NotBlank String name, @NotBlank String date
        )
{
    public static Certificate of(String name, String date) {
        return new Certificate(name, date);
    }
}
