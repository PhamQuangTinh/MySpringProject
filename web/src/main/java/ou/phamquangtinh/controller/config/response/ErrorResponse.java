package ou.phamquangtinh.controller.config.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import ou.phamquangtinh.controller.config.DTO.ErrorDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse<T> {
    private ErrorDTO<T> error;

    public ErrorResponse(T object, String message) {
        error = new ErrorDTO<T>(object, message);
    }

    public ErrorResponse(T object) {
        error = new ErrorDTO<T>(object);
    }

    public ErrorDTO<T> getError() {
        return error;
    }

    public void setError(ErrorDTO<T> error) {
        this.error = error;
    }
}