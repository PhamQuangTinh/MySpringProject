package ou.phamquangtinh.controller.config.response;

import ou.phamquangtinh.controller.config.DTO.ErrorDTO;
import ou.phamquangtinh.controller.config.DTO.SuccessDTO;

public class SuccessResponse<T> {
    private SuccessDTO<T> data;

    private ErrorDTO<T> error;

    private boolean success = true;

    public SuccessResponse() {
    }

    public SuccessResponse(T object, boolean isSuccess) {
        success = isSuccess;
        if (success) {
            this.data = new SuccessDTO<>(object);
        } else {
            this.error = new ErrorDTO<>(object);
        }
    }

    public SuccessResponse(T object, String message) {
        this.data = new SuccessDTO<>(object, message);
    }

    public SuccessResponse(T object, Integer length, String message) {
        this.data = new SuccessDTO<>(object, length, message);
    }

    public SuccessResponse(T object, Integer length) {
        this.data = new SuccessDTO<>(object, length);
    }

    public SuccessDTO<T> getData() {
        return data;
    }

    public void setData(SuccessDTO<T> data) {
        this.data = data;
    }

    public ErrorDTO<T> getError() {
        return error;
    }

    public void setError(ErrorDTO<T> error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
