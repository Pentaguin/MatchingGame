import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.ArrayList;

class MatchingGame {
    public static void main(String[] args) {
        Board board = new Board();
        board.createBoard(4, 4);
    }
}

class Board implements ActionListener {
    JButton firstOpenedCard = null;
    JFrame frame;
    int setFound = 0;
    int cells;

    public void createBoard(int rows, int cols) {
        frame = new JFrame();
        cells = rows * cols;
        ArrayList<Integer> cardList = new ArrayList<Integer>();

        for (int i = 1; i <= cells; i++) {
            if (i % 2 == 0) {
                cardList.add(i / 2);
            } else {
                cardList.add((i + 1) / 2);
            }
        }

        Collections.shuffle(cardList);

        for (Integer i = 0; i < cells; i++) {
            JButton button = new JButton(cardList.get(i).toString());
            button.setActionCommand(cardList.get(i).toString());
            button.addActionListener(this);
            button.setBackground(Color.white);
            frame.add(button);
        }

        frame.setTitle("Matching Game");
        frame.setLayout(new GridLayout(rows, cols));
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Setting default close operation
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton openedCard = (JButton) e.getSource();
        if (firstOpenedCard == null) {
            firstOpenedCard = openedCard;
            firstOpenedCard.setBackground(Color.green);
        } else {
            if (firstOpenedCard == openedCard) {
                return;
            } else if (firstOpenedCard.getActionCommand().equals(openedCard.getActionCommand())) {
                System.out.println("Set found");

                firstOpenedCard.setBackground(Color.cyan);
                openedCard.setBackground(Color.cyan);

                firstOpenedCard.removeActionListener(this);
                openedCard.removeActionListener(this);

                firstOpenedCard = null;
                setFound++;

                if (setFound == cells) {
                    // end game
                }
            } else {
                openedCard.setBackground(Color.green);

                // delay here

                firstOpenedCard.setBackground(Color.white);
                openedCard.setBackground(Color.white);
                firstOpenedCard = null;
            }

        }

        System.out.println(openedCard.getActionCommand());

    }
}