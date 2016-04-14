package game.model;

import game.controller.GameField;
import game.view.MainGame;

public class Human extends Player{

    public Human(GameField _field, char dot) {
        targetField = _field;
        this.dot = dot;
    }

    // запрос хода игрока
    @Override
    public void turn() {
        int x, y;

        do {
            System.out.println("Куда вы хотите поставить " + dot + " ? (формат: x y)");
            x = MainGame.sc.nextInt() - 1;
            y = MainGame.sc.nextInt() - 1;
        } while (!targetField.isCellEmpty(x, y)); // поле должно быть пустым

        targetField.setChar(x, y, dot);
    }
}

