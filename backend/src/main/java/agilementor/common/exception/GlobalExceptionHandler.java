package agilementor.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleSocialLoginFailException(
        SocialLoginFailException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleExternalServerException(
        ExternalServerErrorException exception) {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleMemberNotFoundException(
        MemberNotFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleProjectNotFoundException(
        ProjectNotFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ExceptionResponse(exception.getMessage()));
    }

}
