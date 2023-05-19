import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Option_Nums extends JPanel implements ActionListener {
    JButton[] selection;
    public Point location;
    Game_Frame frame;
     public static int savedNum;
    public Option_Nums(Game_Frame frame) {
        savedNum=1;
        this.frame=frame;
        selection = new JButton[9];
        location=null;
        this.setBounds(0, Consts.PANEL_SIZE+50+50, 60 * 9, 60);
        this.setLayout(null);

        createBoard(selection);

        for (int i = 0; i < 9; i++) {
            this.add(selection[i]);
        }

        this.setVisible(true);
    }

    public void createBoard(JButton[] selection) {
        for (int i = 0; i < 9; i++) {
            selection[i] = new JButton(String.valueOf(i + 1));
            selection[i].setBackground(Color.BLUE);
            selection[i].setForeground(Color.WHITE);
            selection[i].setSize(60, 60);
            selection[i].setLocation(60 * i, 0);
            selection[i].addActionListener(this);
            selection[i].setActionCommand(""+(i+1));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String action =e.getActionCommand();
        savedNum=Integer.parseInt(action);

    }
    public static int getSavedNum()
    {
        return  savedNum ;
    }
}