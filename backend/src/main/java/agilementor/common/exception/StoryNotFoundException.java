package agilementor.common.exception;

public class StoryNotFoundException extends RuntimeException {

    private static final String MESSAGE = "해당 스토리를 찾을 수 없습니다.";

    public StoryNotFoundException() {
        super(MESSAGE);
    }
}
