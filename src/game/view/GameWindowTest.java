package game.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindowTest extends JFrame {

    JPanel panel = new JPanel();
    JButton[][] buttons = new JButton[3][3];


    public void init() {
        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("XOGame");

        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton jButton = new JButton();
                jButton.setText(j % 2 == 0 ? "X" : "O");
                buttons[i][j] = jButton;
                panel.add(jButton);
                final int finalI = i;
                final int finalJ = j;
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String buttonText = e.getActionCommand();
                        System.out.printf("Button: %s, x: %d, y: %d\n", buttonText, finalI, finalJ);


                    }
                });
            }
        }

        add(panel);
    }
}
