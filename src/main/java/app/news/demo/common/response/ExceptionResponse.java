package app.news.demo.common.response;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String message,
        String detailedMessage,
        LocalDateTime errorTime
) {
}
