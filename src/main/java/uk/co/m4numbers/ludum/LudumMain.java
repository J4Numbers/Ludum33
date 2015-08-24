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

import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;

import org.jsfml.system.Clock;
import org.jsfml.system.Time;

import org.jsfml.window.event.Event;

import uk.co.m4numbers.ludum.design.Genesis;
import uk.co.m4numbers.ludum.design.Level;
import uk.co.m4numbers.ludum.logic.Enemy;
import uk.co.m4numbers.ludum.logic.KeyboardInterrupts;

import java.util.Map;
import java.util.Timer;

public class LudumMain {

    public static RenderWindow window;
    public static Map<String, Texture> textures;
    public static Map<String, SoundBuffer> sounds;
    public static Map<String, Level> levels;
    public static Map<String, Font> fonts;

    public static final Integer scalingConst = 2;

    public static Timer spawner;
    public static Integer stage = 1;
    public static Boolean spawn = true;
    public static Clock totalTime;
    public static Clock timeSince;
    public static float finalTime;

    public static float horizontalVelocity;
    public static float verticalVelocity;
    public static Level currentLevel;

    public static boolean isDead = false;

    public static void main(String[] args) {

        try {

            window = Genesis.dayZero();
            textures = Genesis.dayOne();
            sounds = Genesis.dayTwo();
            levels = Genesis.dayThree();
            fonts = Genesis.dayFour();

            Genesis.daySeven();

            float fps;
            Text txt;

            while (window.isOpen()) {

                for (Event event = window.pollEvent(); event != null; event = window.pollEvent())
                    KeyboardInterrupts.handleEvent(event);

                window.clear();
                Time elapse = timeSince.restart();

                fps = 1.f / elapse.asSeconds();

                float fpsSmoothing = 1 / (fps/60);

                if (!isDead) {

                    currentLevel.player.movePlayerCharacter(horizontalVelocity, verticalVelocity, fpsSmoothing);
                    for (Enemy e : currentLevel.enemySet) {
                        e.move(fpsSmoothing, elapse.asSeconds());
                    }
                    currentLevel.ui.render(elapse.asSeconds());

                    if (spawn) {
                        spawn = false;
                        currentLevel.releaseEnemies(stage);
                        ++stage;
                    }
                    currentLevel.render();

                } else {
                    spawner.cancel();
                    if (currentLevel.player.hasJustDied()) {
                        finalTime = totalTime.getElapsedTime().asSeconds();
                    }
                    txt = new Text(
                            String.format("You lasted %.2f seconds. Congrats, press R to restart", finalTime),
                            fonts.get("scrawl_tmp.ttf"), 32
                    );
                    txt.setOrigin(txt.getLocalBounds().width / 2, txt.getLocalBounds().height / 2);
                    txt.setPosition(window.getSize().x / 2, window.getSize().y / 2);
                    txt.setColor(Color.WHITE);
                    window.draw(txt);
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
