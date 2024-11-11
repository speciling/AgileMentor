package agilementor.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleSocialLoginFailException(
        SocialLoginFailException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleExternalServerException(
        ExternalServerErrorException ex) {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(new ExceptionResponse(ex.getMessage()));
    }
}
