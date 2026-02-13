package food;

public class Radish extends Vegetable{
    public Radish() {
        super();
    }

    @Override
    public String toString() {
        return "редис";
    }

    @Override
    public void peel() {
        this.changeTaste(Math.random()*0.1);
    }
}
