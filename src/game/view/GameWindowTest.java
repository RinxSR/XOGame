package game.view;

import javax.swing.*;
import java.awt.*;

public class GameWindowTest extends JFrame {

    public void init() {
        setVisible(true);
        setSize(400, 400);
        add(new Button("Hello"));
    }
}
