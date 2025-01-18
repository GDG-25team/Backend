package gdg.team25.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

public record AddressResponse(
        @NotBlank String address
)
{
    public static AddressResponse from(String address) {
        return new AddressResponse(address);
    }
}
