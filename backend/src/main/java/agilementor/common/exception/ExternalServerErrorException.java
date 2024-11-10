package agilementor.common.exception;

public class ExternalServerErrorException extends RuntimeException {

    private static final String MESSAGE = "외부 api 서버에 문제가 발생했습니다.;";

    public ExternalServerErrorException() {
        super(MESSAGE);
    }
}
