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
import java.util.Random;
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

    public MonsterUI ui;
    public Set<Sprite> floorSet;
    public Set<Sprite> wallSet;
    public Player player;
    public Set<Enemy> enemySet;

    public Pair startingPoint;

    public Level(
            Map<Pair, Integer> levelMap, Map<Integer, Texture> textureMap,
            Map<Pair, Integer> enemyMap, Pair charStart) {
        this.levelMap = levelMap;
        this.textureMap = textureMap;
        this.enemyMap = enemyMap;
        this.startingPoint = charStart;

        this.clear();

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

    public void clear() {
        this.ui = new MonsterUI();

        this.floorSet = new HashSet<Sprite>();
        this.wallSet = new HashSet<Sprite>();
        this.enemySet = new HashSet<Enemy>();

        this.player = new Player(new Sprite(textureMap.get(5), new IntRect(0, 0, 8, 8)), startingPoint);
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

    public Sprite createSprite(EnemyEnums type) {
        Sprite s = new Sprite(textureMap.get(type.texture), new IntRect(0, 0, 8, 8));
        s.setScale(new Vector2f(LudumMain.scalingConst, LudumMain.scalingConst));
        s.setOrigin(new Vector2f(
                s.getLocalBounds().width / 2,
                s.getLocalBounds().height / 2
        ));

        Random rnd = new Random();
        Pair p;

        do {
            int x = rnd.nextInt(30);
            int y = rnd.nextInt(16);
             p = new Pair(x, y);
        } while (levelMap.get(p) != 0);

        s.setPosition(new Vector2f(
                p.x * (16 * LudumMain.scalingConst) + (16 * LudumMain.scalingConst) / 2,
                p.y * (16 * LudumMain.scalingConst) + (16 * LudumMain.scalingConst) / 2
        ));

        return s;
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

        spawnEnemy(1, EnemyEnums.TOURIST);
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
                    if (wall.getGlobalBounds().contains(ret) || player.isColliding(ret)) {
                        //System.out.printf("Limit for line found at %f/%f\n", ret.x, ret.y);
                        return Vector2f.sub(ret, initial);
                    }
                }
            }
        }

        return Vector2f.sub(ret, initial);

    }

    public void nextFrame(Sprite actor, Integer start) {
        this.nextFrame(actor, start, actor.getTexture().getSize().x/8);
    }

    public void nextFrame(Sprite actor, Integer start, Integer end) {
        IntRect planned = new IntRect(
                actor.getTextureRect().left + 8, actor.getTextureRect().top,
                actor.getTextureRect().width, actor.getTextureRect().height
        );
        if (planned.left >= end * 8 || actor.getTextureRect().left < start*8) {
            actor.setTextureRect(new IntRect(
                            start*8, 0, actor.getTextureRect().width,
                            actor.getTextureRect().height)
            );
        } else {
            actor.setTextureRect(planned);
        }
    }

    public void stopFrame(Sprite actor) {
        actor.setTextureRect(new IntRect(0, 0, 8, 8));
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

        for (Sprite wall : LudumMain.currentLevel.wallSet) {
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

    public void render() {
        for (Sprite s: floorSet) {
            LudumMain.window.draw(s);
        }
        for (Sprite s: wallSet) {
            LudumMain.window.draw(s);
        }
        ui.draw(LudumMain.window);
        for (Enemy e: enemySet) {
            e.checkSeen(player.getCurrentBox());
            e.isTouching(player.getCurrentBox());
            e.draw(LudumMain.window);
        }
        if (player != null) {
            player.draw(LudumMain.window);
        }
    }

    public void killEnemy(Set<Enemy> e) {
        for (Enemy f: e)
            enemySet.remove(f);
    }

    private void spawnEnemy(int count, EnemyEnums type) {
        try {
            for (int i=0; i<count; ++i) {
                enemySet.add(
                        (Enemy) type.reflector.getConstructor(Sprite.class).newInstance(createSprite(type))
                );
            }
        } catch (Exception e) {
        }
    }

    public void releaseEnemies(Integer key) {
        spawnEnemy( key / 10, EnemyEnums.EXTERMINATOR);
        spawnEnemy((key % 10) / 3, EnemyEnums.SOILDIER);
        spawnEnemy((key % 10) % 3, EnemyEnums.TOURIST);
    }
}
