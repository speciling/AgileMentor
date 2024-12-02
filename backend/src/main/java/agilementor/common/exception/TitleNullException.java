package agilementor.common.exception;

public class TitleNullException extends RuntimeException {

    private static final String MESSAGE = "제목을 입력하지 않았습니다. 제목을 입력해주세요.";

    public TitleNullException() {
        super(MESSAGE);
    }
}
