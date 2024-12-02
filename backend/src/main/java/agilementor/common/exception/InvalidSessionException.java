package agilementor.common.exception;

public class InvalidSessionException extends RuntimeException {

    private static final String MESSAGE = "세션이 유효하지 않습니다. 다시 로그인해주세요.";

    public InvalidSessionException() {
        super(MESSAGE);
    }
}
