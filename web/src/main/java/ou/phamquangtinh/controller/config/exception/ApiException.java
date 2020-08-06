package ou.phamquangtinh.controller.config.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiException {
    private final String message;
    private final Throwable throwable;
    //    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;

    public ApiException(String message, Throwable throwable, LocalDateTime localDateTime) {
        this.message = message;
        this.throwable = throwable;
        this.localDateTime = localDateTime;
    }


}
