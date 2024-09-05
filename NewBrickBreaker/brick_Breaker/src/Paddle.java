import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

public class Paddle extends Rectangle2D.Double implements KeyListener {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 15;
    private static final int MOVE_DISTANCE = 5;
    private boolean leftPressed, rightPressed;

    public Paddle(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    public void move() {
        if (leftPressed && x > 0) {
            x -= MOVE_DISTANCE;
        }
        if (rightPressed && x < 800 - WIDTH) {
            x += MOVE_DISTANCE;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
