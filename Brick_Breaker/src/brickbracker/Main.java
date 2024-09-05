package brickbracker; // Corrected package name from brickbracker to brickbreaker

import javax.swing.JFrame; // Import the Gameplay class

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame instance to serve as the main window
        JFrame obj = new JFrame();

        // Create an instance of the Gameplay class
        Gameplay gamePlay = new Gameplay();

        // Set the dimensions of the window
        obj.setBounds(10, 10, 700, 600);

        // Set the title of the window
        obj.setTitle("Brick Breaker");

        // Prevent the window from being resized
        obj.setResizable(false);

        // Make the window visible
        obj.setVisible(true);

        // Specify the operation that will happen by default when the user initiates a "window closing" action
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the Gameplay panel to the JFrame
        obj.add(gamePlay);
    }
}
