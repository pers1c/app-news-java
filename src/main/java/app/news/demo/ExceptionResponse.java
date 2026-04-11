package app.news.demo;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String message,
        String detailedMessage,
        LocalDateTime errorTime
) {
}
