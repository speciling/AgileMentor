package agilementor.common.exception;

public class SprintNotFoundException extends RuntimeException {

    private static final String MESSAGE = "해당 스프린트를 찾을 수 없습니다.";

    public SprintNotFoundException() {
        super(MESSAGE);
    }
}
