import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private static final int SIZE = 20;
    private double dx = 2, dy = -2;

    public Ball(int x, int y) {
        super(x, y, SIZE, SIZE);
    }

    public void move() {
        x += dx;
        y += dy;

        if (x <= 0 || x >= 800 - SIZE) {
            dx = -dx;
        }

        if (y <= 0) {
            dy = -dy;
        }
    }

    public void reverseYDirection() {
        dy = -dy;
    }

    public void reverseXDirection() {
        dx = -dx;
    }
}
