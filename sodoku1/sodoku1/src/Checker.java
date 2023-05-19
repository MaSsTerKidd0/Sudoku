import javax.swing.*;
public class Checker extends JButton
{
    int size;
    int row;
    int col;
    boolean changeable;
    int buttonx;
    int buttony;
    private boolean helped;
    private boolean gameUserSolve;
    private boolean gameStartingPoint;
    public Checker(int size, int row, int col, boolean changeable, int num )
    {
        this.size=size;
        this.row=row;
        this.col=col;
        this.changeable=changeable;
        this.buttonx=row*Consts.CHECKER_SIZE;
        this.buttony=col*Consts.CHECKER_SIZE;

        this.helped = false;
        this.gameUserSolve = false;
        this.gameStartingPoint = false;

        if (num != 0)
        {
           this.setText(Integer.toString(num));
        }
        this.setSize(Consts.CHECKER_SIZE,Consts.CHECKER_SIZE);
        this.setLocation(row*Consts.CHECKER_SIZE, 50+col*Consts.CHECKER_SIZE);
        if((col >= 3) && col%3 == 0 )
        {
            this.setLocation(row*Consts.CHECKER_SIZE, 55+col*Consts.CHECKER_SIZE);
        }
        if((row >= 3) && (row%3) == 0 )
        {
            this.setLocation(5+row*Consts.CHECKER_SIZE, 50+col*Consts.CHECKER_SIZE);
        }
        if((col >= 3) && (row >= 3) && (row%3)==0 && (col%3) == 0)
        {
            this.setLocation(5+row*Consts.CHECKER_SIZE, 55+col*Consts.CHECKER_SIZE);
        }


    }
    public void draw(JPanel panel)
    {
        panel.add(this);
    }

    public void setSolveByUser(boolean state) {
        this.gameUserSolve = state;
    }
    public boolean isGameUserSolve() {
        return this.gameUserSolve;
    }
    public void setWasHelped(boolean state) {
        this.helped = state;
    }
    public boolean isHelped() {
        return helped;
    }

    public void setGameStartingPoint(boolean gameStartingPoint) {
        this.gameStartingPoint = gameStartingPoint;
    }
    public boolean getGameStartingPoint() {
        return gameStartingPoint;
    }
}
