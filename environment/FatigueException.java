package environment;

public class FatigueException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Энергия закончилась.";
    }
}
