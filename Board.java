import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.ArrayList;

public class Board implements ActionListener 
{
    private JButton firstOpenedCard = null;
    private JFrame frame;
    private int setFound = 0;
    private int cells;
    private Timer timer;
    private ImageIcon cardImage = new ImageIcon("Images/back.jpg");
    private boolean stopClick = false;

    public void createBoard(int rows, int cols) 
    {
        frame = new JFrame();
        cells = rows * cols;
        ArrayList<Integer> cardList = new ArrayList<Integer>();

        for (int i = 1; i <= cells; i++) 
        {
            if (i % 2 == 0) 
            {
                cardList.add(i / 2);
            } 
            else 
            {
                cardList.add((i + 1) / 2);
            }
        }

        Collections.shuffle(cardList);

        for (Integer i = 0; i < cells; i++) 
        {
            JButton button = new JButton();
            button.setIcon(cardImage);
            button.setActionCommand(cardList.get(i).toString());
            button.addActionListener(this);
            button.setBackground(Color.white);
            button.setRolloverEnabled(false);
            frame.add(button);
        }

        frame.setTitle("Matching Game");
        frame.setLayout(new GridLayout(rows, cols));
        frame.setSize(1080, 1920);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Setting default close operation
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (stopClick) 
        {
            return;
        }

        JButton openedCard = (JButton) e.getSource();
        if (firstOpenedCard == null) 
        {
            firstOpenedCard = openedCard;
            firstOpenedCard.setIcon(new ImageIcon("Images/" + firstOpenedCard.getActionCommand() + ".jpg"));
        } 
        else 
        {
            if (firstOpenedCard == openedCard) 
            {
                return;
            } 
            else if (firstOpenedCard.getActionCommand().equals(openedCard.getActionCommand())) 
            {
                firstOpenedCard.setIcon(new ImageIcon("Images/" + firstOpenedCard.getActionCommand() + ".jpg"));
                firstOpenedCard.removeActionListener(this);
                firstOpenedCard.setFocusPainted(false);
                firstOpenedCard = null;

                openedCard.setIcon(new ImageIcon("Images/" + openedCard.getActionCommand() + ".jpg"));
                openedCard.removeActionListener(this);
                openedCard.setFocusPainted(false);

                setFound++;

                if (setFound == cells) 
                {
                    // end game
                }
            } 
            else 
            {
                timer = new Timer(1500, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        firstOpenedCard.setIcon(cardImage);
                        openedCard.setIcon(cardImage);
                        firstOpenedCard = null;
                        stopClick = false;
                    }
                });

                stopClick = true;
                openedCard.setIcon(new ImageIcon("Images/" + openedCard.getActionCommand() + ".jpg"));
                timer.setRepeats(false);
                timer.start();

            }
        }
    }
}