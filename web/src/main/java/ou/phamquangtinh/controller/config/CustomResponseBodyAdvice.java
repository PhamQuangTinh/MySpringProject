package ou.phamquangtinh.controller.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ou.phamquangtinh.controller.config.exception.ApiException;
import ou.phamquangtinh.controller.config.response.SuccessResponse;

@RestControllerAdvice(value = "ou.phamquangtinh.controller")
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseBinding.class) == false) {
            if ((!(o instanceof ApiException)) && (!(o instanceof SuccessResponse))) {
                SuccessResponse<Object> responseBody = new SuccessResponse<>(o, true);
                return responseBody;
            } else {
                SuccessResponse<Object> errorResponse = new SuccessResponse<>(o, false);
                return errorResponse;
            }
        }
        return o;
    }

}

