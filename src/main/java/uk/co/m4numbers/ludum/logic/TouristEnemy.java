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

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.co.m4numbers.ludum.LudumMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Class Name - TouristEnemy
 * Package - uk.co.m4numbers.ludum.logic
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public class TouristEnemy extends Enemy {

    public TouristEnemy(Sprite actor) {
        super(actor, TerrorEnums.NORMAL, 80, 30);
    }

    @Override
    protected void touching() {}

    @Override
    protected void react() {
        this.terrorLevel = TerrorEnums.TERRIFIED;
        this.terrorChanged = true;
        this.fromTerrified = actor.getPosition();
    }

    @Override
    protected Vector2f calculateMovement() {

        Vector2f stat = new Vector2f(0,0);
        Random rnd = new Random();
        Integer c;
        float vel = this.terrorLevel.getVelocity();
        Integer chance = this.terrorLevel.getTurnChance();

        Vector2f left = LudumMain.currentLevel.processMove(
                this.actor, -(((16 * LudumMain.scalingConst)-actor.getLocalBounds().width)/2), 0
        );
        Vector2f up = LudumMain.currentLevel.processMove(
                this.actor, 0, -(((16 * LudumMain.scalingConst)-actor.getLocalBounds().height)/2)
        );
        Vector2f right = LudumMain.currentLevel.processMove(
                this.actor, ((16 * LudumMain.scalingConst)-actor.getLocalBounds().width)/2, 0
        );
        Vector2f down = LudumMain.currentLevel.processMove(
                this.actor, 0, ((16 * LudumMain.scalingConst)-actor.getLocalBounds().height)/2
        );

        if (this.horVel == 0 && this.verVel == 0) {
            Vector2f[] dirs = new Vector2f[4];
            int o = 0;
            if (!left.equals(stat)) {
                dirs[o] = left;
                ++o;
            }
            if (!up.equals(stat)) {
                dirs[o] = up;
                ++o;
            }
            if (!right.equals(stat)) {
                dirs[o] = right;
                ++o;
            }
            if (!down.equals(stat)) {
                dirs[o] = down;
                ++o;
            }

            c = rnd.nextInt(o);

            if (dirs[c].equals(left)) {
                horVel = -(vel);
            } else if (dirs[c].equals(up)) {
                verVel = -(vel);
            } else if (dirs[c].equals(right)) {
                horVel = vel;
            } else if (dirs[c].equals(down)) {
                verVel = vel;
            }
        } else
        if (this.horVel != 0) {
            if (left.equals(stat) && right.equals(stat)) {
                this.horVel = 0;
            }
            if (!up.equals(stat) || !down.equals(stat)) {
                c = rnd.nextInt(chance);
                if (c == 0) {
                    this.horVel = 0;

                    c = rnd.nextInt(2);
                    if (c==0 && !up.equals(stat)) {
                        this.verVel = -(vel);
                    } else if (!down.equals(stat)) {
                        this.verVel = vel;
                    }

                }
            }
            if (horVel > 0) {
                if (right.equals(stat)) {
                    c = rnd.nextInt(2);
                    this.horVel = 0;
                    if (c==0 && !up.equals(stat)) {
                        this.verVel = -(vel);
                    } else if (!down.equals(stat)) {
                        this.verVel = vel;
                    } else {
                        horVel = -(vel);
                    }
                }
            } else if (horVel < 0) {
                if (left.equals(stat)) {
                    c = rnd.nextInt(2);
                    this.horVel = 0;
                    if (c==0 && !up.equals(stat)) {
                        this.verVel = -(vel);
                    } else if (!down.equals(stat)) {
                        this.verVel = vel;
                    } else {
                        horVel = vel;
                    }
                }
            }
        } else if (this.verVel != 0) {
            if (up.equals(stat) && down.equals(stat)) {
                this.verVel = 0;
            }
            if (!left.equals(stat) || !right.equals(stat)) {
                c = rnd.nextInt(chance);
                if (c == 0) {
                    this.verVel = 0;

                    c = rnd.nextInt(2);
                    if (c==0 && !left.equals(stat)) {
                        this.horVel = -(vel);
                    } else if (!right.equals(stat)) {
                        this.horVel = vel;
                    }
                }
            }
            if (verVel > 0) {
                if (down.equals(stat)) {
                    c = rnd.nextInt(2);
                    this.verVel = 0;
                    if (c==0 && !left.equals(stat)) {
                        this.horVel = -(vel);
                    } else if (!right.equals(stat)) {
                        this.horVel = vel;
                    } else {
                        verVel = -(vel);
                    }
                }
            } else if (verVel < 0) {
                if (up.equals(stat)) {
                    c = rnd.nextInt(2);
                    this.verVel = 0;
                    if (c==0 && !left.equals(stat)) {
                        this.horVel = -(vel);
                    } else if (!right.equals(stat)) {
                        this.horVel = vel;
                    } else {
                        verVel = vel;
                    }
                }
            }
        }

        return new Vector2f(horVel, verVel);
    }

}
