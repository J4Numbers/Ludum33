package uk.co.m4numbers.ludum.logic;

/**
 * Class Name - TerrorEnums
 * Package - uk.co.m4numbers.ludum.logic
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public enum TerrorEnums {
    NORMAL(1, 0.2f, 5),
    PARANOID(1.2f, 0.1f, 3),
    TERRIFIED(2.3f, 0.05f, 10);

    private float velocity;
    private float movementInterval;
    private Integer turnChance;

    TerrorEnums(float i, float j, Integer k) {
        this.velocity = i;
        this.movementInterval = j;
        this.turnChance = k;
    }

    public float getVelocity() {
        return velocity;
    }

    public float getMovementInterval() {
        return movementInterval;
    }

    public Integer getTurnChance() {
        return turnChance;
    }

}
