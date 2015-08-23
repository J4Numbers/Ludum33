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

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import uk.co.m4numbers.ludum.LudumMain;
import uk.co.m4numbers.ludum.logic.*;
import uk.co.m4numbers.ludum.utils.Pair;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class Name - Level
 * Package - uk.co.m4numbers.ludum.design
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public class Level {

    private Map<Pair, Integer> levelMap;
    private Map<Integer, Texture> textureMap;
    private Map<Pair, Integer> enemyMap;
    private Set<Sprite> floorSet;
    private Set<Sprite> wallSet;

    public Sprite player;
    public Set<Enemy> enemySet;

    public Level(
            Map<Pair, Integer> levelMap, Map<Integer, Texture> textureMap,
            Map<Pair, Integer> enemyMap, Pair charStart) {
        this.levelMap = levelMap;
        this.textureMap = textureMap;
        this.enemyMap = enemyMap;

        this.floorSet = new HashSet<Sprite>();
        this.wallSet = new HashSet<Sprite>();
        this.enemySet = new HashSet<Enemy>();

        this.player = new Sprite(textureMap.get(5));
        this.player.setScale(LudumMain.scalingConst, LudumMain.scalingConst);
        this.player.setOrigin(new Vector2f(
                this.player.getLocalBounds().width / 2,
                this.player.getLocalBounds().height / 2
        ));
        this.player.setPosition(new Vector2f(
                charStart.x * (16 * LudumMain.scalingConst) + (16 * LudumMain.scalingConst) / 2,
                charStart.y * (16 * LudumMain.scalingConst) + (16 * LudumMain.scalingConst) / 2
        ));

        System.out.printf(
                "There are %d entries in the level, and %d textures present\n",
                levelMap.size(), textureMap.size()
        );

        //for (Map.Entry<Pair, Integer> e: levelMap.entrySet()) {
        //    System.out.printf("Item at location %d/%d is %d\n", e.getKey().x, e.getKey().y, e.getValue());
        //}

        for (Map.Entry<Integer, Texture> text : this.textureMap.entrySet()) {
            System.out.printf("Texture at loc %d is %s\n", text.getKey(), text.getValue().toString());
        }
    }

    private IntRect getSpriteRotated(Pair location, Integer value) {
        String rep = "";

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; ++j) {
                if (location.x + i < 0 || location.x + i > 29 || location.y + j < 0 || location.y + j > 15) {
                    rep += Integer.toString(1);
                } else {
                    Pair p = new Pair(location.x + i, location.y + j);
                    if (levelMap.containsKey(p))
                        rep += (levelMap.get(p).equals(value)) ? 1 : 0;
                    else
                        rep += Integer.toString(1);
                }
            }
        }

        System.out.printf("Square registered as %s\n", rep);

        if (rep.equals("100010000") || rep.equals("001010000")
                || rep.equals("000010100") || rep.equals("000010001")
                || rep.equals("000010000") || rep.equals("101010101")
                || rep.equals("101010100") || rep.equals("001010101")
                || rep.equals("101010001") || rep.equals("100010101")
                || rep.equals("101010000") || rep.equals("001010100")
                || rep.equals("100010001") || rep.equals("000010101")
                || rep.equals("100010100") || rep.equals("001010001")) {
            return new IntRect(13 * 16, 0, 16, 16);
        } else if (rep.equals("010010000") || rep.equals("000110000")
                || rep.equals("000011000") || rep.equals("000010010")
                || rep.equals("111010000") || rep.equals("100110100")
                || rep.equals("001011001") || rep.equals("000010111")
                || rep.equals("110010000") || rep.equals("011010000")
                || rep.equals("001011000") || rep.equals("000011001")
                || rep.equals("000010011") || rep.equals("000010110")
                || rep.equals("000110100") || rep.equals("100110000")) {
            return new IntRect(12 * 16, 0, 16, 16);
        } else if (rep.equals("010010010") || rep.equals("000111000")) {
            return new IntRect(11 * 16, 0, 16, 16);
        } else if (rep.equals("011011011") || rep.equals("000111111")
                || rep.equals("110110110") || rep.equals("111111000")) {
            return new IntRect(10*16, 0, 16, 16);
        } else if (rep.equals("011011010") || rep.equals("000111011")
                || rep.equals("010110110") || rep.equals("110111000")) {
            return new IntRect(9*16, 0, 16, 16);
        } else if (rep.equals("010011010") || rep.equals("000111010")
                || rep.equals("010110010") || rep.equals("010111000")) {
            return new IntRect(8*16, 0, 16, 16);
        } else if (rep.equals("000011011") || rep.equals("000110110")
                || rep.equals("110110000") || rep.equals("011011000")) {
            return new IntRect(7*16, 0, 16, 16);
        } else if (rep.equals("000011010") || rep.equals("000110010")
                || rep.equals("010110000") || rep.equals("010011000")) {
            return new IntRect(6*16, 0, 16, 16);
        } else if (rep.equals("010111010")) {
            return new IntRect(5*16, 0, 16, 16);
        } else if (rep.equals("110111010") || rep.equals("011111010")
                || rep.equals("010111110") || rep.equals("010111011")) {
            return new IntRect(4 * 16, 0, 16, 16);
        } else if (rep.equals("010111111") || rep.equals("110111110")
                || rep.equals("111111010") || rep.equals("011111011")) {
            return new IntRect(3*16, 0, 16, 16);
        } else if (rep.equals("110111011") || rep.equals("011111110")) {
            return new IntRect(2*16, 0, 16, 16);
        } else if (rep.equals("011111111") || rep.equals("110111111")
                || rep.equals("111111011") || rep.equals("111111110")) {
            return new IntRect(16, 0, 16, 16);
        }
        return new IntRect(0, 0, 16, 16);
    }

    public void load() {
        Sprite s;
        for (Map.Entry<Pair, Integer> entry : levelMap.entrySet()) {
            System.out.printf(
                    "Adding pos (%d, %d) as %d to spritemap\n",
                    entry.getKey().x, entry.getKey().y, entry.getValue()
            );

            s = new Sprite(textureMap.get(entry.getValue()), getSpriteRotated(entry.getKey(), entry.getValue()));

            s.setScale(new Vector2f(LudumMain.scalingConst, LudumMain.scalingConst));
            s.setPosition(new Vector2f(
                    entry.getKey().x * (16 * LudumMain.scalingConst),
                    entry.getKey().y * (16 * LudumMain.scalingConst)
            ));

            if (entry.getValue() == 0) {
                floorSet.add(s);
            } else {
                wallSet.add(s);
            }
        }

        System.out.printf("All Environ sprites loaded...\n");

        EnemyEnums t;
        for (Map.Entry<Pair, Integer> e : enemyMap.entrySet()) {

            if (e.getValue() == 0) {
                t = EnemyEnums.TOURIST;
            } else if (e.getValue() == 1) {
                t = EnemyEnums.SOILDIER;
            } else {
                t = EnemyEnums.EXTERMINATOR;
            }

            System.out.printf(
                    "Adding enemy of type: %s to pos (%d/%d) in spritemap\n",
                    t, e.getKey().x, e.getKey().y
            );

            s = new Sprite(textureMap.get(e.getValue() + 10));
            s.setScale(new Vector2f(LudumMain.scalingConst, LudumMain.scalingConst));
            s.setOrigin(new Vector2f(
                    s.getLocalBounds().width / 2,
                    s.getLocalBounds().height / 2
            ));
            s.setPosition(new Vector2f(
                    e.getKey().x * (16 * LudumMain.scalingConst) + (16 * LudumMain.scalingConst) / 2,
                    e.getKey().y * (16 * LudumMain.scalingConst) + (16 * LudumMain.scalingConst) / 2
            ));

            switch (t) {
                case TOURIST:
                    enemySet.add(new TouristEnemy(s));
                    break;
                case SOILDIER:
                    enemySet.add(new SoldierEnemy(s));
                    break;
                case EXTERMINATOR:
                    enemySet.add(new ExterminatorEnemy(s));
                    break;
            }
        }
    }

    public void setAllEnemyTo(TerrorEnums te) {
        for (Enemy e: enemySet) {
            e.setTerrorLevel(te);
        }
    }

    public Vector2f limitLine(Vertex[] vertexes, Vector2f initial) {

        Vector2f ret = initial;

        for (int i=0; i<vertexes.length; ++i) {

            //System.out.printf("Vertex %d is being checked\n", i);
            if (vertexes[i] != null) {
                ret = vertexes[i].position;

                for (Sprite wall : wallSet) {
                    if (wall.getGlobalBounds().contains(ret) || player.getGlobalBounds().contains(ret)) {
                        //System.out.printf("Limit for line found at %f/%f\n", ret.x, ret.y);
                        return Vector2f.sub(ret, initial);
                    }
                }
            }
        }

        return Vector2f.sub(ret, initial);

    }

    public Vector2f processMove(Sprite actor, float horMov, float verMov) {

        Vector2f position = new Vector2f(actor.getGlobalBounds().left, actor.getGlobalBounds().top);
        Vector2f planned = Vector2f.add(position, new Vector2f(horMov, verMov));

        Vector2f plannedHTL = Vector2f.add(position, new Vector2f(horMov, 0));
        Vector2f plannedHTR = Vector2f.add(plannedHTL, new Vector2f(actor.getGlobalBounds().width, 0));
        Vector2f plannedHBL = Vector2f.add(plannedHTL, new Vector2f(0, actor.getGlobalBounds().height));
        Vector2f plannedHBR = Vector2f.add(plannedHTR, new Vector2f(0, actor.getGlobalBounds().height));

        Vector2f plannedVTL = Vector2f.add(position, new Vector2f(0, verMov));
        Vector2f plannedVTR = Vector2f.add(plannedVTL, new Vector2f(actor.getGlobalBounds().width, 0));
        Vector2f plannedVBL = Vector2f.add(plannedVTL, new Vector2f(0, actor.getGlobalBounds().height));
        Vector2f plannedVBR = Vector2f.add(plannedVTR, new Vector2f(0, actor.getGlobalBounds().height));

        boolean blockHor = false;
        boolean blockVer = false;

        for (Sprite wall : wallSet) {
            blockHor = blockHor || (wall.getGlobalBounds().contains(plannedHTL) || wall.getGlobalBounds().contains(plannedHTR)
                    || wall.getGlobalBounds().contains(plannedHBL) || wall.getGlobalBounds().contains(plannedHBR));

            blockVer = blockVer || (wall.getGlobalBounds().contains(plannedVTL) || wall.getGlobalBounds().contains(plannedVTR)
                    || wall.getGlobalBounds().contains(plannedVBL) || wall.getGlobalBounds().contains(plannedVBR));
        }

        Vector2f globalTL = Vector2f.sub(
                new Vector2f(LudumMain.window.getSize().x, LudumMain.window.getSize().y),
                new Vector2f(planned.x, planned.y)
        );
        Vector2f globalTR = Vector2f.add(globalTL, new Vector2f(actor.getGlobalBounds().width, 0));
        Vector2f globalBL = Vector2f.add(plannedHTL, new Vector2f(0, actor.getGlobalBounds().height));
        Vector2f globalBR = Vector2f.add(plannedHTR, new Vector2f(0, actor.getGlobalBounds().height));

        if (globalTL.x < 0 || globalTL.x > LudumMain.window.getSize().x
                || globalTR.x < 0 || globalTR.x > LudumMain.window.getSize().x
                || globalBL.x < 0 || globalBL.x > LudumMain.window.getSize().x
                || globalBR.x < 0 || globalBR.x > LudumMain.window.getSize().x)
            blockHor = true;

        if (globalTL.y < 0 || globalTL.y > LudumMain.window.getSize().y
                || globalTR.y < 0 || globalTR.y > LudumMain.window.getSize().y
                || globalBL.y < 0 || globalBL.y > LudumMain.window.getSize().y
                || globalBR.y < 0 || globalBR.y > LudumMain.window.getSize().y)
            blockVer = true;

        return new Vector2f((blockHor) ? 0 : horMov, (blockVer) ? 0 : verMov);
    }

    public void movePlayerCharacter(float horizontalVelocity, float verticalVelocity, float fpsSmoothing) {

        Vector2f move = processMove(player, (horizontalVelocity * fpsSmoothing), (verticalVelocity * fpsSmoothing));

        float rot=player.getRotation();
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
        }

        player.setPosition(
                Vector2f.add(
                        player.getPosition(), move
                )
        );

        player.setRotation(rot);
    }

    public void render() {
        for (Sprite s: floorSet) {
            LudumMain.window.draw(s);
        }
        for (Sprite s: wallSet) {
            LudumMain.window.draw(s);
        }
        for (Enemy e: enemySet) {
            e.checkSeen(player.getGlobalBounds());
            e.draw(LudumMain.window);
        }
        if (player != null) {
            LudumMain.window.draw(player);
        }
    }

}
