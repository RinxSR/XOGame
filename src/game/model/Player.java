package game.model;

import game.controller.GameField;

/**
 * Абстрактный класс Player. От него наследуются все, кто будет играть.
 */
public abstract class Player {

    /**
     * Ссылка на игровое поле.
     */
    protected GameField targetField;
    /**
     * "Крестик" или "нолик".
     */
    protected char dot;

    /**
     * Возвращает "крестик" или "нолик".
     *
     * @return the dot
     */
    public char getDot() {
        return dot;
    }

    /**
     * Абстрактный метод хода.
     */
    public abstract void turn();
}

