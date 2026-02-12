package food;

public class Garlic extends Vegetable{
    public Garlic() {
        super();
    }

    @Override
    public String toString() {
        return "чеснок";
    }

    @Override
    public void peel() {
        this.changeTaste(Math.random()*0.2);
    }
}