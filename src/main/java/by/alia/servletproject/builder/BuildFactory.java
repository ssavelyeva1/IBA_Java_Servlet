package by.alia.servletproject.builder;

public class BuildFactory {
    private static final String USER = "user";
    private static final String PERSON = "personas";
    private static final String MESSAGE= "Unknown Builder name!";

    public static Builder create(String builderName) {
        switch (builderName) {
            case USER: {
                return new UserBuilder();
            } case PERSON: {
                return new PersonBuilder();
            }
            default: throw new IllegalArgumentException(MESSAGE);
        }
    }
}
