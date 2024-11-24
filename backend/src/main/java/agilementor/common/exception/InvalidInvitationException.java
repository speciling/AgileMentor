package agilementor.common.exception;

public class InvalidInvitationException extends RuntimeException {

    private static final String MESSAGE = "유효하지 않은 초대입니다.";

    public InvalidInvitationException() {
        super(MESSAGE);
    }
}
