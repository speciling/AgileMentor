package agilementor.common.exception;

public class NotProjectAdminException extends RuntimeException {

    private static final String MESSAGE = "프로젝트 관리자가 아닙니다.";

    public NotProjectAdminException() {
        super(MESSAGE);
    }
}
