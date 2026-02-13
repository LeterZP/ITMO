package food;

import java.util.ArrayList;

public class Carrot extends Vegetable{
    public Carrot() {
        super();
    }

    @Override
    public String toString() {
        return "морковь";
    }

    @Override
    public void peel() {
        this.changeTaste(Math.random()*0.2);
    }
}
