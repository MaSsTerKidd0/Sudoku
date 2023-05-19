import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//import javax.swing.JOptionPane;
public class utility_buttons extends JPanel implements ActionListener
{
    JButton helpButton;
    JButton cheekButton;
    JButton clearButton;
    JButton clearAllButton;
    JButton solveAllButton;
    Game_Frame fframe;
    public utility_buttons(Game_Frame frame)
    {
        fframe=frame;
        this.setBounds(0, 0, 80*7, 30);
        this.setLayout(null);
        Solve.solveduko();
        // Help button
        helpButton = new JButton("Help");
        helpButton.setSize(80, 30);
        helpButton.setLocation(0, 0);
        helpButton.setBackground(Color.BLUE);
        helpButton.setForeground(Color.white);

        // Cheek button
        cheekButton = new JButton("Check");
        cheekButton.setSize(80, 30);
        cheekButton.setLocation(80, 0);
        cheekButton.setBackground(Color.BLUE);
        cheekButton.setForeground(Color.white);
        // Clear button
        clearButton = new JButton("Clear");
        clearButton.setSize(80, 30);
        clearButton.setLocation(160, 0);
        clearButton.setBackground(Color.BLUE);
        clearButton.setForeground(Color.white);

        //clear All Button
        clearAllButton = new JButton("ClearBoard");
        clearAllButton.setSize(160, 30);
        clearAllButton.setLocation(240, 0);
        clearAllButton.setBackground(Color.BLUE);
        clearAllButton.setForeground(Color.white);

        solveAllButton = new JButton("Solve Board");
        solveAllButton.setSize(160, 30);
        solveAllButton.setLocation(400, 0);
        solveAllButton.setBackground(Color.BLUE);
        solveAllButton.setForeground(Color.white);

        clearButton.addActionListener(this);
        cheekButton.addActionListener(this);
        helpButton.addActionListener(this);
        clearAllButton.addActionListener(this);
        solveAllButton.addActionListener(this);

        this.add(clearButton);
        this.add(cheekButton);
        this.add(helpButton);
        this.add(clearAllButton);
        this.add(solveAllButton);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the buttons
        helpButton.repaint();
        cheekButton.repaint();
        clearButton.repaint();
        clearAllButton.repaint();
        solveAllButton.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Random rand = new Random();

        int i,j,counter=0;
        if (e.getSource() == clearButton && !fframe.lastEnter.empty())
        {
            Game_Panel.curr.setText("");
        }
        if (e.getSource() == cheekButton)
        {

            for(i=0;i<9;i++)
            {
                for (j=0;j<9;j++)
                {

                    if(Game_Panel.Cboard[i][j].getText() != "")
                    {
                        if (Integer.parseInt(Game_Panel.Cboard[i][j].getText()) == Solve.fullgrid[j][i]) {
                            counter++;
                        }
                    }
                }
            }
            if(counter==81)
            {
                JFrame ffframe=new JFrame();
                JOptionPane.showMessageDialog(ffframe, "solved!");
            }
        }

        if (e.getSource() ==helpButton)
        {
            int max=9,min=0;
            int randomNum = rand.nextInt((max - min) ) + min;
            int randomNum2 = rand.nextInt((max - min) ) + min;

            while (Game_Panel.Cboard[randomNum][randomNum2].isHelped() || Game_Panel.Cboard[randomNum][randomNum2].isGameUserSolve() || Game_Panel.Cboard[randomNum][randomNum2].getGameStartingPoint()) {
                randomNum = rand.nextInt((max - min)) + min;
                randomNum2 = rand.nextInt((max - min)) + min;
            }
                //Game_Panel.solveAl.solvedGrid[i][j];
            Game_Panel.Cboard[randomNum][randomNum2].setText(String.valueOf(Solve.fullgrid[randomNum2][randomNum]));
            Game_Panel.Cboard[randomNum][randomNum2].setForeground(Color.MAGENTA);
            Game_Panel.Cboard[randomNum][randomNum2].setSolveByUser(false);
            Game_Panel.Cboard[randomNum][randomNum2].setWasHelped(true);
        }
        if(e.getSource() == clearAllButton)
        {
            //clear User Choices
            for(i=0;i<9;i++)
            {
                for (j=0;j<9;j++)
                {
                    if(Game_Panel.Cboard[i][j].isGameUserSolve())
                    {
                        Game_Panel.Cboard[i][j].setSolveByUser(false);
                        Game_Panel.Cboard[i][j].setText("");;
                        Game_Panel.Cboard[i][j].setWasHelped(false);
                    }
                }
            }
        }
        if(e.getSource() == solveAllButton)
        {
            for(i=0;i<9;i++)
            {
                for (j=0;j<9;j++)
                {
                    if(!Game_Panel.Cboard[i][j].isHelped() && !Game_Panel.Cboard[i][j].getGameStartingPoint())
                    {
                        Game_Panel.Cboard[i][j].setSolveByUser(true);
                        Game_Panel.Cboard[i][j].setText(String.valueOf(Solve.fullgrid[j][i]));
                        Game_Panel.Cboard[i][j].setForeground(Color.MAGENTA);
                        Game_Panel.Cboard[i][j].setWasHelped(true);
                    }
                }
            }
        }

    }
}
