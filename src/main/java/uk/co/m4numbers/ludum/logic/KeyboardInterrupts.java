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

import org.jsfml.graphics.IntRect;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;
import uk.co.m4numbers.ludum.LudumMain;
import uk.co.m4numbers.ludum.design.Genesis;

import java.security.Key;

/**
 * Class Name - KeyboardInterrupts
 * Package - uk.co.m4numbers.ludum.logic
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 21/08/2015
 */
public class KeyboardInterrupts {

    public static void handleEvent(Event e) {
        if (e.type == Event.Type.CLOSED)
            LudumMain.window.close();

        if (e.type == Event.Type.KEY_PRESSED) {

            KeyEvent ke = e.asKeyEvent();

            switch (ke.key) {
                case A:
                    LudumMain.currentLevel.setAllEnemyTo(TerrorEnums.NORMAL);
                    break;
                case B:
                    LudumMain.currentLevel.setAllEnemyTo(TerrorEnums.PARANOID);
                    break;
                case C:
                    LudumMain.currentLevel.setAllEnemyTo(TerrorEnums.TERRIFIED);
                    break;
                case D:
                    LudumMain.sounds.get("drums.mid").play();
                    break;
                case DOWN:
                    LudumMain.verticalVelocity = 2;
                    break;
                case UP:
                    LudumMain.verticalVelocity = -2;
                    break;
                case RIGHT:
                    LudumMain.horizontalVelocity = 2;
                   // LudumMain.sprites[2].setTextureRect(new IntRect(0, 0, 24, 24));
                    break;
                case LEFT:
                    LudumMain.horizontalVelocity = -2;
                   // LudumMain.sprites[2].setTextureRect(new IntRect(24, 0, -24, 24));
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
                    if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
                        LudumMain.verticalVelocity = -2;
                    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
                        LudumMain.verticalVelocity = 2;
                    } else {
                        LudumMain.verticalVelocity = 0;
                    }
                    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT)) {
                        LudumMain.horizontalVelocity = -2;
                    } else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) {
                        LudumMain.horizontalVelocity = 2;
                    }
                    break;
                case LEFT:
                case RIGHT:
                    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT)) {
                        LudumMain.horizontalVelocity = -2;
                    } else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) {
                        LudumMain.horizontalVelocity = 2;
                    } else {
                        LudumMain.horizontalVelocity = 0;
                    }
                    if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
                        LudumMain.verticalVelocity = -2;
                    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
                        LudumMain.verticalVelocity = 2;
                    }
                    break;
            }
        }
    }

}
