package uk.co.m4numbers.ludum;
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

/**
 * Class Name - LudumMain
 * Package - uk.co.m4numbers.ludum
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 20/08/2015
 */

import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;

import org.jsfml.system.Clock;
import org.jsfml.system.Time;

import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import uk.co.m4numbers.ludum.design.Genesis;
import uk.co.m4numbers.ludum.design.Level;
import uk.co.m4numbers.ludum.logic.Enemy;
import uk.co.m4numbers.ludum.logic.KeyboardInterrupts;

import java.util.Map;
import java.util.Set;

public class LudumMain {

    public static RenderWindow window;
    public static Map<String, Texture> textures;
    public static Map<String, Sound> sounds;
    public static Map<String, Level> levels;

    public static final Integer scalingConst = 2;

    public static float horizontalVelocity;
    public static float verticalVelocity;
    public static Level currentLevel;

    public static boolean isPlaying = true;

    public static void main(String[] args) {

        try {

            window = Genesis.dayZero();
            textures = Genesis.dayOne();
            sounds = Genesis.dayTwo();
            levels = Genesis.dayThree();

            currentLevel = levels.get("level_one.json");

            currentLevel.load();

            System.out.printf("Welcome to the jungle.\n");

            float fps;
            Clock c = new Clock();
            float moveAssist = 1;

            while (window.isOpen()) {

                for (Event event = window.pollEvent(); event != null; event = window.pollEvent())
                    KeyboardInterrupts.handleEvent(event);

                window.clear();
                Time elapse = c.restart();

                fps = 1.f / elapse.asSeconds();

                float fpsSmoothing = 1 / (fps/60);

                if (isPlaying) {

                    moveAssist += elapse.asSeconds();


                    currentLevel.player.movePlayerCharacter(horizontalVelocity, verticalVelocity, fpsSmoothing);
                    for (Enemy e : currentLevel.enemySet) {
                        moveAssist = e.move(fpsSmoothing, moveAssist);
                    }

                    currentLevel.render();

                }
                window.display();

            }

            window.close();

        } catch (Exception e) {
            System.out.printf("An error occurred somewhere in the matrix: %s",
                    e.getMessage());
            e.printStackTrace();
        }

    }
}
