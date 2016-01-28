package game.model;

public abstract class Player {

    protected GameField targetField;
    protected char dot;

    public char getDot() {
        return dot;
    }

    public void setDot(char dot) {
        this.dot = dot;
    }

    abstract void turn();
}

