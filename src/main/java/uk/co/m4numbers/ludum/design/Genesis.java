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

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.window.VideoMode;

import org.json.JSONObject;
import uk.co.m4numbers.ludum.LudumMain;
import uk.co.m4numbers.ludum.fileio.LudumFile;
import uk.co.m4numbers.ludum.utils.LevelFilter;
import uk.co.m4numbers.ludum.utils.Pair;
import uk.co.m4numbers.ludum.utils.SoundFilter;
import uk.co.m4numbers.ludum.utils.SpriteFilter;

import java.io.File;
import java.io.FileNotFoundException;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class Name - Genesis
 * Package - uk.co.m4numbers.ludum.design
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 20/08/2015
 */
public class Genesis {

    public static RenderWindow dayZero() throws Exception {
        try {
            JSONObject conf = LudumFile.loadFile("config.json");
            JSONObject res = conf.getJSONObject("resolution");
            RenderWindow w = new RenderWindow(
                    new VideoMode(res.getInt("width"), res.getInt("height")),
                    "JSFML Testing"
            );
            w.clear(Color.BLACK);

            int fps = conf.getInt("framerate");

            if (fps != 0)
                w.setFramerateLimit(fps);

            return w;
        } catch (Exception e) {
            throw e;
        }
    }

    public static Map<String, Texture> dayOne() throws Exception {
        try {
            Map<String, Texture> textureMap = new HashMap<String, Texture>();

            File spriteFolder = new File("sprites/");
            File[] sprites = spriteFolder.listFiles(new SpriteFilter());

            if (sprites != null) {

                for (File sprite : sprites) {
                    Texture t = new Texture();

                    System.out.printf("Loading %s into the texture map as %s\n", sprite.getName(), sprite.toURI());

                    t.loadFromFile(Paths.get(sprite.toURI()));
                    textureMap.put(sprite.getName(), t);
                }

            }

            return textureMap;

        } catch (FileNotFoundException fnf) {
            throw fnf;
        }
    }

    public static Map<String, Sound> dayTwo() throws Exception {
        try {
            Map<String, Sound> soundMap = new HashMap<String, Sound>();

            SoundBuffer sb;

            File levelFolder = new File("sound/");
            File[] levels = levelFolder.listFiles(new SoundFilter());

            if (levels != null) {

                for (File lvl : levels) {
                    sb = new SoundBuffer();
                    sb.loadFromFile(Paths.get(lvl.toURI()));

                    soundMap.put(lvl.getName(), new Sound(sb));
                    System.out.printf("Adding %s to sounds\n", lvl.getName());
                }

            }

            return soundMap;
        } catch (Exception e) {
            throw e;
        }
    }

    public static Map<String, Level> dayThree() throws Exception {
        try {
            Map<String, Level> levelMap = new HashMap<String, Level>();
            Map<Pair, Integer> enemyMap;
            Map<Pair, Integer> spriteMap;
            Map<Integer, Texture> textureMap;
            Iterator i;
            Iterator j;
            String iter;
            String jter;


            File levelFolder = new File("levels/");
            File[] levels = levelFolder.listFiles(new LevelFilter());

            if (levels != null) {

                for (File lvl : levels) {

                    System.out.printf("Loading in %s\n", lvl.getName());
                    JSONObject level = LudumFile.loadFile(lvl);
                    System.out.printf("File loaded\n");

                    enemyMap = new HashMap<Pair, Integer>();
                    spriteMap = new HashMap<Pair, Integer>();
                    textureMap = new HashMap<Integer, Texture>();

                    i = level.getJSONObject("map").keys();

                    while (i.hasNext()) {

                        iter = (String) i.next();

                        j = level.getJSONObject("map").getJSONObject(iter).keys();

                        while (j.hasNext()) {

                            jter = (String) j.next();

                            spriteMap.put(
                                    new Pair(Integer.valueOf(jter), Integer.valueOf(iter)),
                                    level.getJSONObject("map").getJSONObject(iter).getInt(jter)
                            );

                        }

                    }

                    i = level.getJSONObject("textures").keys();

                    while (i.hasNext()) {
                        iter = (String) i.next();

                        textureMap.put(
                                Integer.valueOf(iter),
                                LudumMain.textures.get(level.getJSONObject("textures").getString(iter))
                        );
                    }

                    i = level.getJSONObject("enemies").keys();

                    while (i.hasNext()) {
                        iter = (String) i.next();

                        enemyMap.put(
                                new Pair(
                                        level.getJSONObject("enemies").getJSONObject(iter).getInt("x"),
                                        level.getJSONObject("enemies").getJSONObject(iter).getInt("y")
                                ), level.getJSONObject("enemies").getJSONObject(iter).getInt("type")
                        );
                    }

                    textureMap.put(5, LudumMain.textures.get("character.png"));
                    textureMap.put(10, LudumMain.textures.get("enemy_0.png"));
                    textureMap.put(11, LudumMain.textures.get("enemy_1.png"));
                    textureMap.put(12, LudumMain.textures.get("enemy_2.png"));

                    levelMap.put(
                            lvl.getName(),
                            new Level(
                                    spriteMap, textureMap, enemyMap,
                                    new Pair(level.getJSONObject("char").getInt("x"), level.getJSONObject("char").getInt("y"))
                            )
                    );

                }

            }

            return levelMap;

        } catch (Exception e) {
            throw e;
        }
    }

}
