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
        //System.out.printf("Actual line registered was: %f/%f\n", actual.x, actual.y);
    }

    public Vertex[] getAllPoints() {
        Vertex[] ret;
        float inc;

        if (Math.abs(actual.x) > Math.abs(actual.y)) {
            ret = new Vertex[(int) Math.floor(Math.abs(actual.x))];
            inc = actual.y / actual.x;
            for (int i = 0; i < Math.floor(Math.abs(actual.x)); ++i) {
                ret[i] = new Vertex(Vector2f.add(a.position, new Vector2f((actual.x > 0) ? i : -i, i * inc)));
            }
        } else {
            ret = new Vertex[(int) Math.floor(Math.abs(actual.y))];
            inc = actual.x / actual.y;
            for (int i = 0; i < Math.floor(Math.abs(actual.y)); ++i) {
                ret[i] = new Vertex(Vector2f.add(a.position, new Vector2f(i * inc, (actual.y > 0) ? -i : i)));
            }
        }

        return ret;
    }

}
