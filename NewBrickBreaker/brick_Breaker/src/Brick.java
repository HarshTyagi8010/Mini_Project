import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Brick extends Rectangle2D.Double {
    private boolean destroyed;

    public Brick(int x, int y) {
        super(x, y, 80, 30);
        this.destroyed = false;
    }

    public void draw(Graphics2D g2d) {
        if (!destroyed) {
            g2d.setColor(Color.RED);
            g2d.fill(this);
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
