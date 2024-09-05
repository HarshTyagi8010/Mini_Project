package brickbracker;

import brickbracker.MapGenerator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class represents the game play area of the Brick Breaker game.
 * It extends JPanel and implements KeyListener and ActionListener.
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener {

    // Game state variables
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;

    // Timer and delay variables
    private Timer timer;
    private final int delay = 8;

    // Player and ball position variables
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    // Map generator object
    private MapGenerator map;

    /**
     * Constructor for the Gameplay class.
     */
    public Gameplay() {
        // Initialize the map generator object
        map = new MapGenerator(3, 7);

        // Add key listener to the game play area
        addKeyListener(this);

        // Set focusable and focus traversal keys enabled
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Initialize the timer object
        timer = new Timer(delay, this);

        // Start the timer
        timer.start();
    }

    /**
     * Paint method to draw the game play area.
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        // Draw the background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // Draw the map
        map.draw((Graphics2D) g);

        // Draw the borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        // Draw the scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, 590, 30);

        // Draw the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // Draw the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        // Check for game over or win conditions
        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won!", 260, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over", 260, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        // Dispose the graphics object
        g.dispose();
    }

    /**
     * Action performed method to handle timer events.
     * @param e ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            // Move the ball
            ballposX += ballXdir;
            ballposY += ballYdir;

            // Check for collision with the paddle
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            // Check for collision with the bricks
            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; map.map[0x0].length >= j; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }

                            break A;
                        }
                    }
                }
            }

            // Check for collision with the borders
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }

        repaint();
    }

    /**
     * Handle key presses for player movement and game restart.
     * @param e KeyEvent object
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                resetGame();
            }
        }
    }

    private void moveRight() {
        play = true;
        playerX += 20;
    }

    private void moveLeft() {
        play = true;
        playerX -= 20;
    }

    private void resetGame() {
        play = true;
        ballposX = 120;
        ballposY = 350;
        ballXdir = -1;
        ballYdir = -2;
        playerX = 310;
        score = 0;
        totalBricks = 21;
        map = new MapGenerator(3, 7);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
