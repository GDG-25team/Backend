package gdg.team25.domain.member.dto.address;

import jakarta.validation.constraints.NotBlank;

public record AddressResponse(
        @NotBlank String address
)
{
    public static AddressResponse from(String address) {
        return new AddressResponse(address);
    }
}
