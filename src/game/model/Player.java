package game.model;

import game.controller.GameField;

public abstract class Player {

    protected GameField targetField;
    protected char dot;

    public char getDot() {
        return dot;
    }

    public void setDot(char dot) {
        this.dot = dot;
    }

    public abstract void turn();
}

