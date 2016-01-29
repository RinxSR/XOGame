package game.model;

public class GameField {
    private static char emptyDot = '.';
    private static int fieldSize;
    private static int needForWin;
    private char[][] field = new char[fieldSize][fieldSize];
    private static int typeOfGame;

    public static int getTypeOfGame() {
        return typeOfGame;
    }

    public static boolean setTypeOfGame(int typeOfGame) {
        if (typeOfGame > 0 && typeOfGame < 4) {
            GameField.typeOfGame = typeOfGame;
            return true;
        }
        return false;
    }

    public GameField() {
        for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++)
                field[i][j] = emptyDot;
    }

    public static char getEmptyDot() {
        return emptyDot;
    }

    public static int getNeedForWin() {
        return needForWin;
    }

    // сеттер количества для победы + проверка
    public static boolean setNeedForWin(int needForWin) {
        if (needForWin <= fieldSize && needForWin > 2) {
            GameField.needForWin = needForWin;
            return true;
        }
        return false;
    }

    public static int getFieldSize() {
        return fieldSize;
    }

    // сеттер размера поля + проверка
    public static boolean setFieldSize(int fieldSize) {
        if (fieldSize > 2) {
            GameField.fieldSize = fieldSize;
            return true;
        }
        return false;
    }

    // запрос размера поля
    public static boolean fieldSizeSetting() {
        System.out.println("Введите размер поля");
        return setFieldSize(MainGame.sc.nextInt());
    }

    // запрос длины линии для победы
    public static boolean needForWinSetting() {
        System.out.println("Введите длину линии для победы");
        return setNeedForWin(MainGame.sc.nextInt());
    }

    // запрос типа игры
    public static void typeOfGame() {
        do {
            System.out.println("Выберите тип игры (число от 1 до 3: ");
            System.out.println("1. человек - человек");
            System.out.println("2. человек - компьютер");
            System.out.println("3. компьютер - компьютер");

        } while (!GameField.setTypeOfGame(MainGame.sc.nextInt()));
    }



    // отрисовка текущего состояния игрового поля
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

    // размещение Х или О в ячейку на поле
    public void setChar(int _x, int _y, char _xo) {
        field[_y][_x] = _xo;
    }

    // проверка пустая ли ячейка
    public boolean isCellEmpty(int _x, int _y) {
        return (_x > -1 && _x < fieldSize && _y > -1 && _y < fieldSize && field[_y][_x] == emptyDot);
    }

    // проверка на свободные ячейки, если не осталось - ничья
    public boolean drawGame() {
        for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++)
                if (field[j][i] == emptyDot)
                    return false;
        System.out.println("Ничья.");
        return true;
    }

    // проверка всего поля на победу Х или О
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

    // перегрузка, мой needForWin, проверка всего поля на победу Х или О
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

    // проверка конкретной линии на победу Х или О
    public boolean checkLine(int _x, int _y, int vx, int vy, char xo, int _needForWin) {
        if (((_x + vx * _needForWin) > fieldSize) || ((_y + vy * _needForWin) > fieldSize) || ((_y + vy * _needForWin) < -1) || ((_x + vx * _needForWin) < -1)) {
            return false;
        }
        for (int i = 0; i < _needForWin; i++)
            if (field[_y + vy * i][_x + vx * i] != xo)
                return false;
        return true;
    }

    // Инраем еще раз?
    public boolean playOnceMore() {
        System.out.println("Хотите сыграть еще раз? yes/no");
        String play = MainGame.sc.next();
        return play.equals("yes");
    }

}
