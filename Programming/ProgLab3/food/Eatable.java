package food;

public interface Eatable {
    double getTaste();
    Color getColor();
    void setTaste(double taste);
    void setColor(Color color);
    void changeTaste(double change);
}
