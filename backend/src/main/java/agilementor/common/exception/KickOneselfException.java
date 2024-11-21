package agilementor.common.exception;

public class KickOneselfException extends RuntimeException {

    private static final String MESSAGE = "자기 자신을 프로젝트에서 추방할 수 없습니다.";

    public KickOneselfException() {
        super(MESSAGE);
    }
}
