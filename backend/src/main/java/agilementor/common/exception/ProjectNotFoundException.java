package agilementor.common.exception;

public class ProjectNotFoundException extends RuntimeException {

    private static final String MESSAGE = "해당 프로젝트를 찾을 수 없습니다.";

    public ProjectNotFoundException() {
        super(MESSAGE);
    }
}
