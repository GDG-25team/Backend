package gdg.team25.domain.member.dto.address;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        @NotBlank String address
) {
}
