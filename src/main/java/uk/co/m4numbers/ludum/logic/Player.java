package uk.co.m4numbers.ludum.logic;
/**
 * Copyright 2015 M. D. Ball (m.d.ball2@ncl.ac.uk)
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jsfml.audio.Sound;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

import uk.co.m4numbers.ludum.LudumMain;
import uk.co.m4numbers.ludum.utils.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * Class Name - Player
 * Package - uk.co.m4numbers.ludum.logic
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 23/08/2015
 */
public class Player {
    protected Sprite actor;

    public boolean died;

    //public Sound movement;
    public Sound eating;

    private boolean biting = false;
    private Clock bTimer = new Clock();

    public Player(Sprite actor, Pair startPos) {
        //movement = new Sound(LudumMain.sounds.get("scuttle.ogg"));
        //movement.setLoop(true);
        eating = new Sound(LudumMain.sounds.get("eating.ogg"));
        this.actor = actor;
        this.actor.setScale(LudumMain.scalingConst, LudumMain.scalingConst);
        this.actor.setOrigin(new Vector2f(
                this.actor.getLocalBounds().width / 2,
                this.actor.getLocalBounds().height / 2
        ));
        this.actor.setPosition(new Vector2f(
                startPos.x * (16 * LudumMain.scalingConst) + (16 * LudumMain.scalingConst) / 2,
                startPos.y * (16 * LudumMain.scalingConst) + (16 * LudumMain.scalingConst) / 2
        ));
    }

    public boolean hasJustDied() {
        if (!died) {
            died = true;
            return true;
        }
        return false;
    }

    public void movePlayerCharacter(float horizontalVelocity, float verticalVelocity, float fpsSmoothing) {

        Vector2f move = LudumMain.currentLevel.processMove(
                actor, (horizontalVelocity * fpsSmoothing), (verticalVelocity * fpsSmoothing)
        );

        float rot = actor.getRotation();

        if (biting) {
            if (bTimer.getElapsedTime().asSeconds() > 0.5)
                biting = false;
            else {
                Set<Enemy> killSet = new HashSet<Enemy>();
                LudumMain.currentLevel.nextFrame(actor, 1, 3);
                 for (Enemy e : LudumMain.currentLevel.enemySet) {
                    if (e.actor.getGlobalBounds().intersection(this.actor.getGlobalBounds()) != null) {
                        LudumMain.currentLevel.ui.delayHealth();
                        e.playDeath();
                        killSet.add(e);
                    }
                }
                LudumMain.currentLevel.killEnemy(killSet);
            }
        } else {
            if (verticalVelocity != 0 || horizontalVelocity != 0) {
                if (verticalVelocity < 0) {
                    rot = 0;
                } else if (horizontalVelocity > 0) {
                    rot = 90;
                } else if (verticalVelocity > 0) {
                    rot = 180;
                } else if (horizontalVelocity < 0) {
                    rot = 270;
                }
                LudumMain.currentLevel.nextFrame(actor, 4);
            } else {
                LudumMain.currentLevel.stopFrame(actor);
            }
        }

        actor.setPosition(
                Vector2f.add(
                        actor.getPosition(), move
                )
        );

        actor.setRotation(rot);
    }

    public FloatRect getCurrentBox() {
        return actor.getGlobalBounds();
    }

    public void bite() {
        biting = true;
        bTimer.restart();
    }

    public boolean isColliding(Vector2f v) {
        return actor.getGlobalBounds().contains(v);
    }

    public void draw(RenderWindow w) {
        w.draw(actor);
    }
}
