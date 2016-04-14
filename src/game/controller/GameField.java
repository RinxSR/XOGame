package game.controller;

import game.view.MainGame;

/**
 * Класс, отвечающий за игровую логику.
 */
public class GameField {
    private static char emptyDot = '.';
    private static int fieldSize;
    private static int needForWin;
    private char[][] field = new char[fieldSize][fieldSize];
    private static int typeOfGame;

    /**
     * Возвращает тип игры.
     *  1. человек - человек;
     *  2. человек - компьютер;
     *  3. компьютер - компьютер"
     *
     * @return тип игры (1, 2, или 3)
     */
    public static int getTypeOfGame() {
        return typeOfGame;
    }

    /**
     * защита от установки некорректных значений typeOfGame.
     *
     * @param typeOfGame тип игры (1, 2, или 3)
     * @return получилось или нет
     */
    public static boolean setTypeOfGame(int typeOfGame) {
        if (typeOfGame > 0 && typeOfGame < 4) {
            GameField.typeOfGame = typeOfGame;
            return true;
        }
        return false;
    }

    /**
     * Конструктор класса.
     * Инициализирует игровое поле
     */
    public GameField() {
        for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++)
                field[i][j] = emptyDot;
    }

    /**
     * возвращает тип поля по умолчанию.
     *
     * @return the empty dot
     */
    public static char getEmptyDot() {
        return emptyDot;
    }

    /**
     * возвращает необходимое количество "крестиков" или "ноликов" для победы.
     *
     * @return the need for win
     */
    public static int getNeedForWin() {
        return needForWin;
    }

    /**
     * Установка необходимое количество "крестиков" или "ноликов" для победы.
     *
     * @param needForWin the need for win
     * @return the need for win
     */
// сеттер количества для победы + проверка
    public static boolean setNeedForWin(int needForWin) {
        if (needForWin <= fieldSize && needForWin > 2) {
            GameField.needForWin = needForWin;
            return true;
        }
        return false;
    }

    /**
     * Возвращает размер поля.
     *
     * @return the field size
     */
    public static int getFieldSize() {
        return fieldSize;
    }

    /**
     * Устанавливает размер поля.
     *
     * @param fieldSize the field size
     * @return the field size
     */
// сеттер размера поля + проверка
    public static boolean setFieldSize(int fieldSize) {
        if (fieldSize > 2) {
            GameField.fieldSize = fieldSize;
            return true;
        }
        return false;
    }

    /**
     * Запрос размера поля от пользователя.
     *
     * @return true - если корректное значение размера поля
     */
    public static boolean fieldSizeSetting() {
        System.out.println("Введите размер поля");
        return setFieldSize(MainGame.sc.nextInt());
    }

    /**
     * Запрос длины линии для победы от пользователя.
     *
     * @return true - если корректное значение длины линии для победы
     */
    public static boolean needForWinSetting() {
        System.out.println("Введите длину линии для победы");
        return setNeedForWin(MainGame.sc.nextInt());
    }

    /**
     * Запрос типа игры от пользователя.
     */
    public static void typeOfGame() {
        do {
            System.out.println("Выберите тип игры (число от 1 до 3: ");
            System.out.println("1. человек - человек");
            System.out.println("2. человек - компьютер");
            System.out.println("3. компьютер - компьютер");

        } while (!GameField.setTypeOfGame(MainGame.sc.nextInt()));
    }


    /**
     * Отрисовка текущего состояния игрового поля.
     */
    public void showField() {
        System.out.print("   ");
        for (int i = 0; i < fieldSize; i++) {
            System.out.printf("%2d ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSize; i++) {
            System.out.printf("%2d  ", i + 1);
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * размещение Х или О в ячейку на поле.
     *
     * @param _x  координата поля по х
     * @param _y  координата поля по у
     * @param _xo "Х" или "О"
     */
    public void setChar(int _x, int _y, char _xo) {
        field[_y][_x] = _xo;
    }

    /**
     * Проверка ячейки на пустоту.
     *
     * @param _x  координата поля по х
     * @param _y  координата поля по у
     * @return true - если ячейка пустая
     */
    public boolean isCellEmpty(int _x, int _y) {
        return (_x > -1 && _x < fieldSize && _y > -1 && _y < fieldSize && field[_y][_x] == emptyDot);
    }

    /**
     * проверка на свободные ячейки на поле.
     *
     * @return true - если ячеек пустых нет (ничья)
     */
    public boolean drawGame() {
        for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++)
                if (field[j][i] == emptyDot)
                    return false;
        System.out.println("Ничья.");
        return true;
    }

    /**
     * Проверка всего поля на победу Х или О.
     *
     * @param xo "Х" или "О"
     * @return true - если длина линии = needForWin
     */
    public boolean checkAllLine(char xo) {
        for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++) {
                if (checkLine(i, j, 1, 0, xo, needForWin)) return true;
                if (checkLine(i, j, 0, 1, xo, needForWin)) return true;
                if (checkLine(i, j, 1, 1, xo, needForWin)) return true;
                if (checkLine(i, j, 1, -1, xo, needForWin)) return true;
            }
        return false;
    }

    /**
     * Проверка всего поля на победу Х или О, с заданием длины линии вручную
     *
     * @param xo "Х" или "О"
     * @param needForWin необходимое длина линии для победы
     * @return true - если длина линии = needForWin
     */
    public boolean checkAllLine(char xo, int needForWin) {
        for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++) {
                if (checkLine(i, j, 1, 0, xo, needForWin)) return true;
                if (checkLine(i, j, 0, 1, xo, needForWin)) return true;
                if (checkLine(i, j, 1, 1, xo, needForWin)) return true;
                if (checkLine(i, j, 1, -1, xo, needForWin)) return true;
            }
        return false;
    }

    /**
     * Проверка конкретной линии на победу Х или О.
     *
     * @param _x координата начала линии по х
     * @param _y координата начала линии по у
     * @param vx "направление" по х
     * @param vy "направление" по у
     * @param xo "Х" или "О"
     * @param _needForWin необходимое длина линии для победы
     * @return true - если длина линии = needForWin
     */
    public boolean checkLine(int _x, int _y, int vx, int vy, char xo, int _needForWin) {
        if (((_x + vx * _needForWin) > fieldSize) || ((_y + vy * _needForWin) > fieldSize) || ((_y + vy * _needForWin) < -1) || ((_x + vx * _needForWin) < -1)) {
            return false;
        }
        for (int i = 0; i < _needForWin; i++)
            if (field[_y + vy * i][_x + vx * i] != xo)
                return false;
        return true;
    }

    /**
     * Предложение продолжить игру.
     *
     * @return true - если пользователь наберет в консоли "yes"
     */
    public boolean playOnceMore() {
        System.out.println("Хотите сыграть еще раз? yes/no");
        String play = MainGame.sc.next();
        return play.equalsIgnoreCase("yes");
    }

}
