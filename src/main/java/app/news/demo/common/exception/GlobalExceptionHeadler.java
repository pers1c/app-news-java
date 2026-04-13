package app.news.demo.common.exception;

import app.news.demo.common.response.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHeadler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHeadler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> headleGenericExption(
            Exception e
    ){
        var error = new ExceptionResponse(
                "Internal server error",
                e.getMessage(),
                LocalDateTime.now()
        );
        log.error("Handler exception: ", e);
        return ResponseEntity.status(500).body(error);
    }

}
