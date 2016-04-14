package game.view;

import game.controller.GameField;
import game.model.Ai;
import game.model.Human;
import game.model.Player;

import java.util.Random;
import java.util.Scanner;

/**
 * Основной класс программы, взаимодействие с пользователем.
 */
public class MainGame {
    /**
     * Объявление переменной GameField.
     */
    public static GameField newField;
    /**
     * Создание статичного сканнера для всего проекта.
     */
    public static Scanner sc = new Scanner(System.in);
    /**
     * Создание статичного рандомайзера для всего проекта.
     */
    public static Random rand = new Random();

    /**
     * Точка входа программы.
     */
    public static void main(String[] args) {

        do {
            while (!GameField.fieldSizeSetting())
                System.out.println("Некорректное зачение");
            while (!GameField.needForWinSetting())
                System.out.println("Некорректное зачение");
            GameField.typeOfGame();



            newField = new GameField();

            Player humanPlayer1 = new Human(newField, 'X');
            Player humanPlayer2 = new Human(newField, 'O');
            Player aiPlayer1 = new Ai(newField, 'X');
            Player aiPlayer2 = new Ai(newField, 'O');

            Player p1 = null;
            Player p2 = null;


            switch (GameField.getTypeOfGame()) {
                case 1:
                    p1 = humanPlayer1;
                    p2 = humanPlayer2;
                    break;
                case 2:
                    p1 = humanPlayer1;
                    p2 = aiPlayer2;
                    break;
                case 3:
                    p1 = aiPlayer1;
                    p2 = aiPlayer2;
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
