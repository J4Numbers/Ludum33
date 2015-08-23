package uk.co.m4numbers.ludum.logic;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import uk.co.m4numbers.ludum.LudumMain;
import uk.co.m4numbers.ludum.utils.ConeShape;

/**
 * Class Name - Enemy
 * Package - uk.co.m4numbers.ludum.logic
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public abstract class Enemy {

    protected Sprite actor;
    protected TerrorEnums terrorLevel;
    protected final float range;
    protected final float biVisAng;

    protected Vector2f prevMove;
    protected ConeShape visionCone;
    protected Vector2f fromTerrified;

    protected float horVel = 0;
    protected float verVel = 0;

    public Enemy(Sprite actor, TerrorEnums terrorLevel, float range, float biVisAng) {
        this.actor = actor;
        this.terrorLevel = terrorLevel;
        this.range = range;
        this.biVisAng = biVisAng;
        this.prevMove = new Vector2f(0, 0);
        this.fromTerrified = new Vector2f(0, 0);
    }

    protected void calculateVision() {

        int rot=0;
        if (verVel < 0) {
            rot = 0;
        } else
        if (horVel > 0) {
            rot = 1;
        } else
        if (verVel > 0) {
            rot = 2;
        } else
        if (horVel < 0) {
            rot = 3;
        }
        this.visionCone = new ConeShape(actor.getPosition(), range, rot*90, biVisAng, 31, terrorLevel);
        this.actor.setRotation(rot * 90f);
    }

    protected abstract Vector2f calculateMovement();

    protected abstract void react();

    public void setTerrorLevel(TerrorEnums te) {
        this.terrorLevel = te;
    }

    public void checkSeen(FloatRect monster) {
        for (Vector2f point: visionCone.getPoints())
            if (monster.contains(point))
                react();
    }

    public float move(float fpsSmoothing, float moveAssist) {

        if (this.terrorLevel == TerrorEnums.TERRIFIED) {
            Vector2f terTracker = Vector2f.sub(actor.getPosition(), fromTerrified);
            if (Math.abs(terTracker.x) > 32*LudumMain.scalingConst && Math.abs(terTracker.y) > 32*LudumMain.scalingConst)
                terrorLevel = TerrorEnums.PARANOID;
        }

        if (moveAssist > terrorLevel.getMovementInterval()) {
            this.prevMove = calculateMovement();
            moveAssist = 0;
        }

        Vector2f proposed = Vector2f.mul(this.prevMove, fpsSmoothing);

        this.actor.setPosition(
               Vector2f.add(LudumMain.currentLevel.processMove(actor,proposed.x, proposed.y),
                        actor.getPosition())
        );
        calculateVision();

        return moveAssist;

    }

    public void draw(RenderWindow w) {
        //System.out.printf("Enemy type: %s moving to %f/%f\n", this.getClass(), actor.getPosition().x, actor.getPosition().y);
        w.draw(actor);
        w.draw(visionCone);
    }

}
