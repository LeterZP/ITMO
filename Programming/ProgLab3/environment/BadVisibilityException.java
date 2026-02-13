package environment;

public class BadVisibilityException extends Exception {
    @Override
    public String getMessage() {
        return "Плохая видимость, дальнейшее изучение локации невозможно.";
    }
}
