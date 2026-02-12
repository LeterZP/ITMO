package food;

public abstract class Fruit implements Eatable{
    private double taste;
    private Color color;

    public Fruit() {
        this.taste = Math.random();
        this.color = Color.values()[(int) (Math.random() * Color.values().length)];
    }

    @Override
    public String toString() {
        return "фрукт";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (this.getClass() == object.getClass()) {
            Fruit fruit = (Fruit) object;
            return this.getTaste() == fruit.getTaste()
                    && this.getColor() == fruit.getColor();
        } return false;
    }

    @Override
    public int hashCode() {
        int total = 0;
        total += (int) (this.getTaste() * Math.pow(10, 8));
        for (int i = 0; i < Color.values().length; i++) {
            if (Color.values()[i] == this.getColor()) {
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
        setTaste(this.taste + change);
    }
}
