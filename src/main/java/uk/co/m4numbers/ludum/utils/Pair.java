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

/**
 * Class Name - Pair
 * Package - uk.co.m4numbers.ludum.utils
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public final class Pair {

    public final Integer x;
    public final Integer y;

    public Pair(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Pair) {
                Pair p = (Pair) o;
                return (this.x.equals(p.x) && this.y.equals(p.y));
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ((13 * this.x) + (27 * this.y)) * 17;
    }

}
