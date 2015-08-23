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

import java.util.HashMap;
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

        for (Map.Entry<Pair, Integer> e: levelMap.entrySet()) {
            System.out.printf("Item at location %d/%d is %d\n", e.getKey().x, e.getKey().y, e.getValue());
        }

        for (Map.Entry<Integer, Texture> text : this.textureMap.entrySet()) {
            System.out.printf("Texture at loc %d is %s\n", text.getKey(), text.getValue().toString());
        }
    }

    public void load() {
        Sprite s;
        Map<Pair, Integer> localScore;
        for (Map.Entry<Pair, Integer> entry : levelMap.entrySet()) {
            System.out.printf(
                    "Adding pos (%d, %d) as %d to spritemap\n",
                    entry.getKey().x, entry.getKey().y, entry.getValue()
            );


            s = new Sprite(textureMap.get(entry.getValue()), new IntRect(0, 0, 16, 16));

            /**if (entry.getValue() == 1) {
                localScore = new HashMap<Pair, Integer>();

                Pair curLoc = entry.getKey();
                int fl;
                int flCount = 0;
                String out = "";

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; ++j) {
                        System.out.printf("Checking location %d/%d\n", curLoc.x + i, curLoc.y + j);
                        if (curLoc.x + i < 0 || curLoc.x + i > 29 || curLoc.y + j < 0 || curLoc.y + j > 15) {
                            fl = 1;
                        } else {
                            System.out.printf("LevelMap entry has string of: %s\n", levelMap.get(new Pair(curLoc.x + i, curLoc.y + j)));
                            Pair p = new Pair(curLoc.x + i, curLoc.y + j);
                            if (levelMap.containsKey(p))
                                fl = levelMap.get(p);
                            else
                                fl = 1;
                        }
                        System.out.printf("Location checked and entered\n");
                        localScore.put(new Pair(i, j), fl);
                        if (fl == 0) ++flCount;
                        out += fl;
                    }
                }

                System.out.printf("Hashcode for map: %s was %d\n", out, localScore.hashCode());
                //BCB//BCC//BBB 1047462224

            }*/
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

            ret = vertexes[i].position;

            for (Sprite wall: wallSet) {
                if (wall.getGlobalBounds().contains(ret)) {
                    System.out.printf("Limit for line found at %f/%f\n", ret.x, ret.y);
                    return ret;
                }
            }

        }

        return ret;

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

       /* float tanL;
        float absX = Math.abs(move.x);
        float absY = Math.abs(move.y);
        float additional;

        if (absX > 0 || absY > 0) {
            //TOA T = Opp / Adj
            if (absX < absY) {
                tanL = absY / absX;
            } else {
                tanL = absX / absY;
            }

            if (move.x >= 0 && move.y >= 0) {
                additional = 0;
            }else if(move.x > 0 && move.y < 0) {
                additional = 90;
            }else if(move.x < 0 && move.y < 0) {
                additional = 180;
            }else{
                additional = 270;
            }

            float angle = (float) Math.toDegrees(Math.atan(tanL));

            player.setRotation(angle + additional);

        }*/

        player.setPosition(
                Vector2f.add(
                        player.getPosition(), move
                )
        );
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
        if (player != null)
            LudumMain.window.draw(player);
    }

}
