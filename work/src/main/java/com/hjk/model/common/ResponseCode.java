package com.hjk.model.common;

public enum ResponseCode {
    SUCCESS(200, "요청에 성공 했습니다"),
    FAILED(500, "요청 실패 했습니다"),
    NOT_FOUND(404, "페이지가 없습니다"),
    UNAUTHORIZED(401, "인증되지 않았습니다"),
    FORBIDDEN(403, "권한이 없습니다");

    private final long code;
    private final String message;

    private ResponseCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
