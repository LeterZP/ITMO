package environment;

public class EmptyCropException extends Exception {
    @Override
    public String getMessage() {
        return "На посеве больше не осталость продуктов.";
    }
}
