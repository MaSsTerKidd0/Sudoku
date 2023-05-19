import javax.swing.*;
import java.util.Stack;


public class Game_Frame extends JFrame
{

    public JPanel currPanel;
    public JPanel currNum;
    public JPanel utBu;
    Stack <Checker>lastEnter;
    public Game_Frame()
    {
        lastEnter = new Stack<>();
        currNum = new Option_Nums(this);
        utBu = new utility_buttons(this);
        currPanel = new Game_Panel(this);


        this.setTitle("sudoku");
        this.setSize(Consts.PANEL_SIZE+15, Consts.PANEL_SIZE+39+50+60+50);///
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("sodoku1/Images/icons8-sudoku-78.png").getImage());

        // ensures the minimum size is enforced.
        this.setMinimumSize(this.getSize());
        this.setVisible(true);

        this.add(utBu);
        this.add(currNum);
        this.add(currPanel);

    }
}

