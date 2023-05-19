import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuMenu extends JFrame {
    public SudokuMenu() {
        setTitle("Sudoku Menu");
        setSize(200, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("sodoku1/Images/icons8-sudoku-78.png").getImage());
        // Create the Start Game button
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                // Direct the user to the game frame
                Game_Frame game = new Game_Frame();
                game.setVisible(true);
                dispose(); // close the menu frame
            }
        });

        // Create the Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0); // Exit the application
            }
        });

        // Add the buttons to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(startButton);
        contentPane.add(exitButton);
    }
}