import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
    private final Timer timer;
    private final Paddle paddle;
    private final Ball ball;
    private final List<Brick> bricks;
    private boolean gameOver;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);

        paddle = new Paddle(350, 550);
        ball = new Ball(390, 500);
        gameOver = false;

        bricks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                bricks.add(new Brick(i * 80, j * 30));
            }
        }

        addKeyListener(paddle);
        timer = new Timer(10, this);
    }

    public void startGame() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (gameOver) {
            drawGameOver(g2d);
            return;
        }

        paddle.move();
        ball.move();
        checkCollisions();

        g2d.setColor(Color.WHITE);
        g2d.fill(paddle);

        g2d.setColor(Color.WHITE);
        g2d.fill(ball);

        g2d.setColor(Color.RED);
        for (Brick brick : bricks) {
            brick.draw(g2d);
        }
    }

    private void drawGameOver(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        String message = "Game Over!";
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        g2d.drawString(message, x, y);
    }

    private void checkCollisions() {
        if (ball.intersects(paddle)) {
            ball.reverseYDirection();
        }

        for (Brick brick : bricks) {
            if (ball.intersects(brick)) {
                ball.reverseYDirection();
                brick.setDestroyed(true);
            }
        }

        bricks.removeIf(Brick::isDestroyed);

        if (ball.y >= getHeight()) {
            gameOver = true;
            timer.stop();
            showGameOverDialog();
        }

        if (bricks.isEmpty()) {
            timer.stop();
            showWinDialog();
        }
    }

    private void showGameOverDialog() {
        JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showWinDialog() {
        JOptionPane.showMessageDialog(this, "You Win!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            repaint();
        }
    }
}
