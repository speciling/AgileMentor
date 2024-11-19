package agilementor.common.exception;

public class SocialLoginFailException extends RuntimeException {

    private static final String MESSAGE = "소셜 로그인에 실패했습니다.";

    public SocialLoginFailException() {
        super(MESSAGE);
    }
}
