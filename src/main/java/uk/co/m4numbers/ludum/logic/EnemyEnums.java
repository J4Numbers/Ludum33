package uk.co.m4numbers.ludum.logic;


/**
 * Class Name - EnemyEnums
 * Package - uk.co.m4numbers.ludum.logic
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public enum EnemyEnums {
    TOURIST(TouristEnemy.class, 10),
    SOILDIER(SoldierEnemy.class, 11),
    EXTERMINATOR(ExterminatorEnemy.class, 12);

    public Class<?> reflector;
    public Integer texture;

    EnemyEnums(Class<?> c, Integer t) {
        this.reflector = c;
        this.texture = t;
    }
}
