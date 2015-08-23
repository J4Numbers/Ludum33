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

/**
 * Class Name - SoldierEnemy
 * Package - uk.co.m4numbers.ludum.logic
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public class SoldierEnemy extends Enemy {

    public SoldierEnemy(Sprite actor) {
        super(actor, TerrorEnums.NORMAL, 30, 30);
    }

    @Override
    protected Vector2f calculateMovement() {
        return new Vector2f(0,0);
    }

    @Override
    protected void react() {}

}
