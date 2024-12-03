package agilementor.common.exception;

public class BacklogNotFoundException extends RuntimeException {

    private static final String MESSAGE = "해당 백로그를 찾을 수 없습니다.";

    public BacklogNotFoundException() {
        super(MESSAGE);
    }
}
