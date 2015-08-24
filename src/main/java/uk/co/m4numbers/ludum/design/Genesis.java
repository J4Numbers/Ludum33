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
import org.jsfml.system.Clock;
import org.jsfml.window.VideoMode;

import org.json.JSONObject;
import uk.co.m4numbers.ludum.LudumMain;
import uk.co.m4numbers.ludum.fileio.LudumFile;
import uk.co.m4numbers.ludum.utils.*;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.*;

import java.nio.file.Paths;
import java.util.*;

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

    public static Map<String, SoundBuffer> dayTwo() throws Exception {
        try {
            Map<String, SoundBuffer> soundMap = new HashMap<String, SoundBuffer>();

            SoundBuffer sb;

            File soundFolder = new File("sound/");
            File[] sounds = soundFolder.listFiles(new SoundFilter());

            if (sounds != null) {

                for (File snd : sounds) {
                    sb = new SoundBuffer();
                    sb.loadFromFile(Paths.get(snd.toURI()));

                    soundMap.put(snd.getName(), sb);
                    System.out.printf("Adding %s to sounds\n", snd.getName());
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

                    textureMap.put(5, LudumMain.textures.get("player.png"));
                    textureMap.put(10, LudumMain.textures.get("tourist.png"));
                    textureMap.put(11, LudumMain.textures.get("soldier.png"));
                    textureMap.put(12, LudumMain.textures.get("enemy_2.png"));

                    levelMap.put(
                            lvl.getName(),
                            new Level(
                                    spriteMap, textureMap,
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

    public static Map<String, Font> dayFour() throws Exception {
        try {
            Map<String, Font> fontMap = new HashMap<String, Font>();

            Font f;

            File fontFolder = new File("fonts/");
            File[] fonts = fontFolder.listFiles(new FontFilter());

            if (fonts != null) {

                for (File font : fonts) {
                    f = new Font();
                    f.loadFromFile(Paths.get(font.toURI()));

                    fontMap.put(font.getName(), f);
                    System.out.printf("Adding %s to fonts\n", font.getName());
                }

            }

            return fontMap;
        } catch (Exception e) {
            throw e;
        }
    }

    public static Sequencer dayFive() throws Exception {
        Sequencer sound = MidiSystem.getSequencer();

        sound.open();

        InputStream is = new BufferedInputStream(
                new FileInputStream(new File("sound/drums.mid"))
        );

        sound.setSequence(is);
        sound.setLoopStartPoint(0);

        return sound;
    }

    public static void daySeven() {
        LudumMain.spawn = true;
        LudumMain.stage = 1;

        LudumMain.currentLevel = LudumMain.levels.get("level_one.json");
        LudumMain.currentLevel.clear();
        LudumMain.currentLevel.load();

        LudumMain.timeSince = new Clock();
        LudumMain.totalTime = new Clock();

        LudumMain.spawner = new Timer();
        LudumMain.spawner.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LudumMain.spawn = true;
            }
        }, 10000, 10000);

        LudumMain.music.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        LudumMain.music.start();

        System.out.printf("Welcome to the jungle.\n");
    }

}
