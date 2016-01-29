package game.model;

import java.util.Random;
import java.util.Scanner;

public class MainGame {
    public static GameField newField;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {

        do {
            while (!GameField.fieldSizeSetting())
                System.out.println("Некорректное зачение");
            while (!GameField.needForWinSetting())
                System.out.println("Некорректное зачение");
            GameField.typeOfGame();

            Player p1;
            Player p2;

            newField = new GameField();

            switch (GameField.getTypeOfGame()) {
                case 1:
                    p1 = new Human(newField, 'X');
                    p2 = new Human(newField, 'O');
                    break;
                case 2:
                    p1 = new Human(newField, 'X');
                    p2 = new Ai(newField, 'O');
                    break;
                case 3:
                    p1 = new Ai(newField, 'X');
                    p2 = new Ai(newField, 'O');
                    break;
            }

            newField.showField();

            while (true) {

                p1.turn();
                if (newField.checkAllLine(p1.getDot())) {
                    newField.showField();
                    System.out.println("Player 1 win!");
                    break;
                }
                newField.showField();
                if (newField.drawGame())
                    break;

                p2.turn();
                if (newField.checkAllLine(p2.getDot())) {
                    newField.showField();
                    System.out.println("Player 2 win!");
                    break;
                }
                newField.showField();

                if (newField.drawGame())
                    break;
            }
        } while (newField.playOnceMore());
    }
}
