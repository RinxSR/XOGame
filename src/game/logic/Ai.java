package game.logic;

public class Ai extends Player{

    private int x, y;
    private char oppositeDot;

    public Ai(GameField _field, char dot) {
        targetField = _field;
        this.dot = dot;
        if (this.dot == 'X')
            oppositeDot = 'O';
        if (this.dot == 'O')
            oppositeDot = 'X';
    }

    // ход компьютера
    @Override
    public void turn() {
        System.out.println("Компьютер делает свой ход:");

        x = MainGame.rand.nextInt(GameField.getFieldSize());
        y = MainGame.rand.nextInt(GameField.getFieldSize());

        // выбираем наилучший вариант x y
        for (int i = 0; i < GameField.getNeedForWin()-1; i++) {

            if (checkMyTurn((GameField.getNeedForWin() - i)))
                break;
            if (checkOppositeTurn((GameField.getNeedForWin() - i)))
                break;

        }

        MainGame.newField.setChar(x, y, dot);
        System.out.printf("Компьютер поставил '%c' в точку %d, %d", dot, x + 1, y + 1);
        System.out.println();
    }

    // проверка линии игрока
    public boolean checkOppositeTurn(int _numbOnLine) {
        for (int i = 0; i < GameField.getFieldSize(); i++) {
            for (int j = 0; j < GameField.getFieldSize(); j++) {
                if (targetField.isCellEmpty(i, j)) {
                    MainGame.newField.setChar(i, j, oppositeDot);
                    if (targetField.checkAllLine(oppositeDot, _numbOnLine)) {
                        x = i;
                        y = j;
                        MainGame.newField.setChar(i, j, GameField.getEmptyDot());
                        return true;
                    }
                    MainGame.newField.setChar(i, j, GameField.getEmptyDot());
                }
            }
        }
        return false;
    }

    // проверка линии компьютера
    public boolean checkMyTurn(int _numbOnLine) {
        for (int i = 0; i < GameField.getFieldSize(); i++) {
            for (int j = 0; j < GameField.getFieldSize(); j++) {
                if (targetField.isCellEmpty(i, j)) {
                    MainGame.newField.setChar(i, j, dot);
                    if (targetField.checkAllLine(dot, _numbOnLine)) {
                        x = i;
                        y = j;
                        MainGame.newField.setChar(i, j, GameField.getEmptyDot());
                        return true;
                    }
                    MainGame.newField.setChar(i, j, GameField.getEmptyDot());
                }
            }
        }
        return false;
    }
}
