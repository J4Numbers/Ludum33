package uk.co.m4numbers.ludum.design;
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

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import uk.co.m4numbers.ludum.LudumMain;

/**
 * Class Name - MonsterUI
 * Package - uk.co.m4numbers.ludum.design
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 23/08/2015
 */
public class MonsterUI {

    private RectangleShape healthBar;
    private final float breaker = 0.1f;
    private int interval;
    private float clocker = 0;

    public MonsterUI() {
        healthBar = new RectangleShape(new Vector2f(LudumMain.window.getSize().x, 10));
        healthBar.setFillColor(Color.RED);
        interval = LudumMain.window.getSize().x / 600;
    }

    public void render(float elapsed) {
        clocker += elapsed;
        if (clocker > breaker) {
            healthBar.setSize(new Vector2f(healthBar.getSize().x - interval, 10));
            clocker = 0;
            if (healthBar.getSize().x <= 0)
                LudumMain.isDead = true;
        }
    }

    public void delayHealth() {
        healthBar.setSize(new Vector2f(healthBar.getSize().x + (interval * 50), 10));
    }

    public void draw(RenderWindow w) {
        w.draw(healthBar);
    }
}
