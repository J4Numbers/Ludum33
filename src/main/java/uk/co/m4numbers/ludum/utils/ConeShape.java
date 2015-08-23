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

import org.jsfml.graphics.Color;
import org.jsfml.graphics.ConvexShape;
import org.jsfml.system.Vector2f;

import uk.co.m4numbers.ludum.LudumMain;
import uk.co.m4numbers.ludum.logic.TerrorEnums;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name - ConeShape
 * Package - uk.co.m4numbers.ludum.utils
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 22/08/2015
 */
public class ConeShape extends ConvexShape {

    public ConeShape(Vector2f start, float dist, float direction, float biAngle, Integer pointCount, TerrorEnums te) {
        setPointCount(3 + pointCount);
        setOrigin(start);
        setPosition(start);

        //System.out.printf("Calculating cone for dir: %f at dist: %f with bisected angle of %f\n", direction, dist, biAngle);

        switch (te) {
            case NORMAL:
                setFillColor(new Color(0, 128, 0, 128));
                break;
            case PARANOID:
                setFillColor(new Color(0, 0, 128, 128));
                break;
            case TERRIFIED:
                setFillColor(new Color(128, 0, 0, 128));
                break;
        }

        float workingAnglePos = direction + biAngle;
        float workingAngleNeg = direction - biAngle;

        addLimitedPoint(0, workingAnglePos, dist);
        addLimitedPoint(1, 0, 0);
        addLimitedPoint(2, workingAngleNeg, dist);

        if (pointCount > 0) {

            float angInterval = (biAngle * 2) / (pointCount+1);
            float curAng = angInterval;
            int i;

            for (i = 0; i < pointCount; ++i) {
                addLimitedPoint(i+3, workingAngleNeg + curAng, dist);
                curAng += angInterval;
            }

        }
    }

    private void addLimitedPoint(int pointCount, float angle, float distance) {
        float cVer = ((float) (Math.cos(Math.toRadians(angle)) * distance));
        float cHor = ((float) (Math.sin(Math.toRadians(angle)) * distance));

        Line l = new Line(getPosition(), Vector2f.add(getPosition(), new Vector2f(cHor, cVer)));
        Vector2f limited = LudumMain.currentLevel.limitLine(l.getAllPoints(), getPosition());
        setPoint(pointCount, Vector2f.add(limited, getPosition()));
    }
}
