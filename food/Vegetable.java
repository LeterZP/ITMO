package food;

public abstract class Vegetable implements Eatable{
    private double taste;
    private Color color;
    private State cookedState;

    public Vegetable() {
        this.taste = Math.random() * 0.5;
        this.color = Color.values()[(int) (Math.random() * Color.values().length)];
        this.cookedState = State.RAW;
    }

    @Override
    public String toString() {
        return "овощ";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (this.getClass() == object.getClass()) {
            Vegetable vegetable = (Vegetable) object;
            return this.getTaste() == vegetable.getTaste()
                    && this.getColor() == vegetable.getColor()
                    && this.getCookedState() == vegetable.getCookedState();
        } return false;
    }

    @Override
    public int hashCode() {
        int total = 0;
        total += (int) (this.getTaste() * Math.pow(10, 7));
        for (int i = 0; i < Color.values().length; i++) {
            if (Color.values()[i] == this.getColor()) {
                total += (int) (i * Math.pow(10, 7));
                break;
            }
        }
        for (int i = 0; i < State.values().length; i++) {
            if (State.values()[i] == this.getCookedState()) {
                total += (int) (i * Math.pow(10, 8));
                break;
            }
        }
        return total;
    }

    @Override
    public double getTaste() {
        return this.taste;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setTaste(double taste) {
        this.taste = taste;
        if (this.taste > 1) this.taste = 1;
        else if (this.taste < 0) this.taste = 0;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void changeTaste(double change) {
        this.setTaste(this.taste + change);
    }

    public State getCookedState() {
        return this.cookedState;
    }

    public void setCookedState(State cookedState) {
        this.cookedState = cookedState;
    }

    public abstract void peel();
}
