package service;

public enum NoInputTypes {
    DATA("ZonedDateTime"),
    ID("UUID");

    private String name;
    NoInputTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
