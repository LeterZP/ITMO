package food;

import java.util.ArrayList;

public class Potato extends Vegetable{
    public Potato() {
        super();
    }

    @Override
    public String toString() {
        return "картофель";
    }

    @Override
    public void peel() {
        this.changeTaste(Math.random()*0.1);
    }
}
