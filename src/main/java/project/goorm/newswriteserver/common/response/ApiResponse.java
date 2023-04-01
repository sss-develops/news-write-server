package project.goorm.newswriteserver.common.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public final class ApiResponse<T> {

    private static final String SUCCESS = "SUCCESS";

    private final LocalDateTime eventTime;
    private HttpStatus status;
    private final int code;
    private final String message;
    private CommonInformationResponse commonInformation;
    private T data;

    private ApiResponse(T data) {
        this.eventTime = LocalDateTime.now();
        this.status = HttpStatus.OK;
        this.message = SUCCESS;
        this.code = HttpStatus.OK.value();
        this.data = data;
    }

    private ApiResponse(HttpStatus status, T data) {
        this.eventTime = LocalDateTime.now();
        this.status = status;
        this.message = SUCCESS;
        this.code = HttpStatus.OK.value();
        this.data = data;
    }

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> of(HttpStatus status, T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(null);
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public CommonInformationResponse getCommonInformation() {
        return commonInformation;
    }

    public T getData() {
        return data;
    }

    public void setCommonResponse(CommonInformationResponse commonInformation) {
        this.commonInformation = commonInformation;
    }
}
