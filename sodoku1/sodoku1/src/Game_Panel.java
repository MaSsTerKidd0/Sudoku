import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Game_Panel extends JPanel implements ActionListener
{
    public static Checker[][] Cboard= new Checker[Consts.CHECKER_AMOUNT][Consts.CHECKER_AMOUNT];
    public static Solve solveAl;
    Game_Frame frame;
    public static Checker curr;
    public Game_Panel(Game_Frame frame)
    {
        this.frame = frame;
        this.setBounds(0,0, Consts.PANEL_SIZE, Consts.CHECKER_SIZE);
        this.setLayout(null);

        //this.setBackground(Color.BLACK);
        solveAl = new Solve();
        solveAl.create_full(0,0);
        solveAl.createSodukoPuzzle();


        Solve.printArray(Solve.fullgrid);
        System.out.println("------------------------");
        Solve.printArray(Solve.puzzeldGrid);
        setBoard(Cboard);
        for(int rowOffset = 0; rowOffset < Consts.CHECKER_AMOUNT  ; rowOffset++) {
            for (int colOffset = 0; colOffset < Consts.CHECKER_AMOUNT; colOffset++) {
                Cboard[rowOffset][colOffset].addActionListener(this);
                Cboard[rowOffset][colOffset].setBackground(new Color(192,192,192));
                this.add(Cboard[rowOffset][colOffset]);
            }
        }
        this.setVisible(true);
    }
    public void setBoard(Checker[][] board)
    {
        for(int i = 0; i < Consts.CHECKER_AMOUNT; i++)
        {
            for(int j = 0; j < Consts.CHECKER_AMOUNT; j++)
            {
                Cboard[i][j] = new Checker(Consts.CHECKER_SIZE,i , j, true, Solve.puzzeldGrid[j][i]);
                Cboard[i][j].setForeground(new Color(128,0,32));
                if(Cboard[i][j].getText() != "")
                {
                    Cboard[i][j].setGameStartingPoint(true);
                }

            }
        }
    }

   @Override
   public void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       for (int row = 0; row < Consts.CHECKER_AMOUNT; row++) {
           for (int col = 0; col < Consts.CHECKER_AMOUNT; col++) {
               // Draw the current cell
               Cboard[row][col].draw(this);
           }
       }
       g.setColor(Color.BLACK);
       g.fillRect(0, 45, getWidth(), getHeight() - 150);
   }

    public void draw()
    {

        //this code is a MUST, for refreshing the screen
        this.setVisible(false);
        this.setVisible(true);
        this.revalidate();

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Checker btn = (Checker) e.getSource();
        curr = btn;
        if(!btn.isHelped() && !btn.getGameStartingPoint()) {
            btn.setText("" + Option_Nums.getSavedNum());
            btn.setForeground(Color.red);
            btn.setSolveByUser(true);
            frame.lastEnter.push(btn);
        }
    }

    public static Solve getSolveAl()
    {
        return solveAl;
    }
}
