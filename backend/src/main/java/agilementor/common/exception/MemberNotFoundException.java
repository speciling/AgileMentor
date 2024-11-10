package agilementor.common.exception;

public class MemberNotFoundException extends RuntimeException {

    private static final String MESSAGE = "해당 회원이 존재하지 않습니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
