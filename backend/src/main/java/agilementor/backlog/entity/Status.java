package agilementor.backlog.entity;

public enum Status {
    TODO("Todo"),
    IN_PROGRESS("InProgress"),
    DONE("Done");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
