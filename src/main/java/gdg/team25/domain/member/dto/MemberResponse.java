package gdg.team25.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

public record MemberResponse(
        @NotBlank String userId
)
{
    public static MemberResponse from(String userId) {
        return new MemberResponse(userId);
    }
}
