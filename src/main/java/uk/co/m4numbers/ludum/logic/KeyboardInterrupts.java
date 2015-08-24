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

import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

import uk.co.m4numbers.ludum.LudumMain;
import uk.co.m4numbers.ludum.design.Genesis;

/**
 * Class Name - KeyboardInterrupts
 * Package - uk.co.m4numbers.ludum.logic
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 21/08/2015
 */
public class KeyboardInterrupts {

    public static int number = 1;

    public static void handleEvent(Event e) {
        if (e.type == Event.Type.CLOSED)
            LudumMain.window.close();

        if (e.type == Event.Type.KEY_PRESSED) {

            KeyEvent ke = e.asKeyEvent();

            switch (ke.key) {
                case E:
                    LudumMain.currentLevel.releaseEnemies(number);
                    ++number;
                    break;
                case R:
                    if (LudumMain.isDead) {
                        LudumMain.isDead = false;
                        Genesis.daySeven();
                    }
                    break;
                case P:
                    if (!LudumMain.isPlaying) {
                        LudumMain.isPlaying = true;
                        Genesis.daySeven();
                    }
                    break;
                case DOWN:
                case S:
                    LudumMain.verticalVelocity = 2;
                    break;
                case UP:
                case W:
                    LudumMain.verticalVelocity = -2;
                    break;
                case RIGHT:
                case D:
                    LudumMain.horizontalVelocity = 2;
                    break;
                case LEFT:
                case A:
                    LudumMain.horizontalVelocity = -2;
                    break;
                case SPACE:
                    LudumMain.currentLevel.player.bite();
                    LudumMain.currentLevel.player.eating.play();
            }

            if (LudumMain.verticalVelocity != 0 && LudumMain.horizontalVelocity != 0) {
                int hypot = 2;
                float sang = (float) Math.sin(45);
                float cang = (float) Math.cos(45);

                LudumMain.horizontalVelocity = sang * hypot * (LudumMain.horizontalVelocity > 0 ? 1 : -1);
                LudumMain.verticalVelocity = cang * hypot * (LudumMain.verticalVelocity > 0 ? 1 : -1);
            }

        }

        if (e.type == Event.Type.KEY_RELEASED) {
            KeyEvent ke = e.asKeyEvent();

            switch (ke.key) {
                case UP:
                case DOWN:
                case W:
                case S:
                    if (Keyboard.isKeyPressed(Keyboard.Key.UP) || Keyboard.isKeyPressed(Keyboard.Key.W)) {
                        LudumMain.verticalVelocity = -2;
                    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN) || Keyboard.isKeyPressed(Keyboard.Key.S)) {
                        LudumMain.verticalVelocity = 2;
                    } else {
                        LudumMain.verticalVelocity = 0;
                    }
                    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT) || Keyboard.isKeyPressed(Keyboard.Key.A)) {
                        LudumMain.horizontalVelocity = -2;
                    } else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT) || Keyboard.isKeyPressed(Keyboard.Key.D)) {
                        LudumMain.horizontalVelocity = 2;
                    }
                    break;
                case LEFT:
                case RIGHT:
                case A:
                case D:
                    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT) || Keyboard.isKeyPressed(Keyboard.Key.A)) {
                        LudumMain.horizontalVelocity = -2;
                    } else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT) || Keyboard.isKeyPressed(Keyboard.Key.D)) {
                        LudumMain.horizontalVelocity = 2;
                    } else {
                        LudumMain.horizontalVelocity = 0;
                    }
                    if (Keyboard.isKeyPressed(Keyboard.Key.UP) || Keyboard.isKeyPressed(Keyboard.Key.W)) {
                        LudumMain.verticalVelocity = -2;
                    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN) || Keyboard.isKeyPressed(Keyboard.Key.S)) {
                        LudumMain.verticalVelocity = 2;
                    }
                    break;
            }

        }
    }

}
