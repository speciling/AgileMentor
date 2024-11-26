package agilementor.common.exception;

public class EndDateNullException extends RuntimeException {

    private static final String MESSAGE = "종료 날짜를 입력하지 않았습니다. 종료 날짜를 입력해주세요.";

    public EndDateNullException() {
        super(MESSAGE);
    }
}
