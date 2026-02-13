package food;

public class Beetroot extends Vegetable{
    public Beetroot() {
        super();
    }

    @Override
    public String toString() {
        return "свёкла";
    }

    @Override
    public void peel() {
        this.changeTaste(Math.random()*0.1);
    }
}
