package gdg.team25.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public record Carrer(
        @NotBlank String companyName, @NotBlank String work, @NotBlank String date
)
{
    public static Carrer of(String companyName, String work, String date) {
        return new Carrer(companyName, work, date);
    }
}
