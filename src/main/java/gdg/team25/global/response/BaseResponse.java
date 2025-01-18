package gdg.team25.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonPropertyOrder({"resultCode", "resultData"})
public class BaseResponse<T> {

    private final int resultCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T resultData;


    public BaseResponse(HttpStatus status, T data) {
        this.resultCode = status.value();
        this.resultData = data;
    }

    public static <T> BaseResponse<T> of(HttpStatus status, T data) {
        return new BaseResponse<>(status, data);
    }

    public static <T> BaseResponse<T> ok(T data) {
        return of(HttpStatus.OK, data);
    }

}
