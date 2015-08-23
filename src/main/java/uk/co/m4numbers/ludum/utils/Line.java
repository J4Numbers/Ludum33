package uk.co.m4numbers.ludum.utils;
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

import org.jsfml.graphics.Vertex;
import org.jsfml.system.Vector2f;

/**
 * Class Name - Line
 * Package - uk.co.m4numbers.ludum.utils
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public final class Line {

    public final Vertex a;
    public final Vertex b;

    public final Vector2f actual;

    public Line(Vector2f a, Vector2f b) {
        this.a = new Vertex(a);
        this.b = new Vertex(b);
        //->
        //AB
        this.actual = Vector2f.sub(b, a);
    }

    public Vertex[] getAllPoints() {
        Vertex[] ret;

        if (Math.abs(actual.x) > Math.abs(actual.y)) {
            ret = new Vertex[Math.abs((int) Math.floor(actual.x))];
            float inc = actual.y / actual.x;
            if (actual.x > 0) {
                for (int i = 0; i <= Math.floor(actual.x); ++i) {
                    ret[i] = new Vertex(Vector2f.add(a.position, new Vector2f(i, i * inc)));
                }
            } else {
                for (int i = 0; i >= Math.ceil(actual.x); --i) {
                    ret[Math.abs(i)] = new Vertex(Vector2f.add(a.position, new Vector2f(i, i * inc)));
                }
            }
        } else {
            ret = new Vertex[Math.abs((int) Math.floor(actual.y))];
            float inc = actual.x / actual.y;
            if (actual.y > 0) {
                for (int i = 0; i <= Math.floor(actual.y); ++i) {
                    ret[i] = new Vertex(Vector2f.add(a.position, new Vector2f(i, i * inc)));
                }
            } else {
                for (int i = 0; i >= Math.ceil(actual.y); --i) {
                    ret[Math.abs(i)] = new Vertex(Vector2f.add(a.position, new Vector2f(i, i * inc)));
                }
            }
        }

        return ret;
    }

}
