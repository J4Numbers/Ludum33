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

        float verPos;
        float horPos;
        float verNeg;
        float horNeg;
        float cos;
        float sin;

        //if (workingAngleNeg < 0 && workingAngleNeg < 45) {
        sin = (float) Math.sin(Math.toRadians(workingAngleNeg));
        cos = (float) Math.cos(Math.toRadians(workingAngleNeg));
        horNeg = sin * dist;
        verNeg = cos * dist;

        sin = (float) Math.sin(Math.toRadians(workingAnglePos));
        cos = (float) Math.cos(Math.toRadians(workingAnglePos));
        horPos = sin * dist;
        verPos = cos * dist;
        //} else if (workingAngleNeg >= 45 && workingAngleNeg < 135) {
        //
        //}

        //float hor = (float) Math.cos(Math.toRadians((double) workingAngle)) * dist;
        //float ver = -((float) Math.sin(Math.toRadians((double) workingAngle)) * dist);

        Line l2 = new Line(getPosition(), Vector2f.add(getPosition(), new Vector2f(horPos, verPos)));
        Line l3 = new Line(getPosition(), Vector2f.add(getPosition(), new Vector2f(horNeg, verNeg)));
        Vector2f lim2 = LudumMain.currentLevel.limitLine(l2.getAllPoints(), getPosition());
        Vector2f lim3 = LudumMain.currentLevel.limitLine(l3.getAllPoints(), getPosition());

        setPoint(0, lim2);
        //setPoint(0, Vector2f.add(getPosition(),new Vector2f(-(hor), ver)));
        setPoint(1, start);
        setPoint(2, lim3);
        //setPoint(2, Vector2f.add(getPosition(), new Vector2f(hor, ver)));

        if (pointCount > 0) {

            float angInterval = (biAngle * 2) / (pointCount+1);
            float curAng = angInterval;
            int i;
            Line l;

            for (i = 0; i < pointCount/2; ++i) {
                float cHor = -((float) (Math.cos(Math.toRadians((double) (workingAngleNeg + curAng))) * dist));
                float cVer = ((float) (Math.sin(Math.toRadians((double) (workingAngleNeg + curAng))) * dist));

                l = new Line(getPosition(), Vector2f.add(getPosition(), new Vector2f(cHor, cVer)));
                Vector2f limited = LudumMain.currentLevel.limitLine(l.getAllPoints(), getPosition());

                setPoint(i+3, limited);
                //setPoint(i + 3, Vector2f.add(getPosition(), new Vector2f(cHor, cVer)));
                curAng += angInterval;
            }

            if (pointCount % 2 == 1) {
                sin = (float) Math.sin(Math.toRadians(direction));
                cos = (float) Math.cos(Math.toRadians(direction));
                setPoint(i + 3, Vector2f.add(getPosition(), new Vector2f(sin * dist, cos * dist)));
                ++i;
            }

            for (; i < pointCount; ++i) {
                float cHor = ((float) (Math.cos(Math.toRadians((double) (workingAngleNeg + curAng))) * dist));
                float cVer = ((float) (Math.sin(Math.toRadians((double) (workingAngleNeg + curAng))) * dist));

                l = new Line(getPosition(), Vector2f.add(getPosition(), new Vector2f(cHor, cVer)));
                Vector2f limited = LudumMain.currentLevel.limitLine(l.getAllPoints(), getPosition());

                setPoint(i+3, limited);
                //setPoint(i + 3, Vector2f.add(getPosition(), new Vector2f(cHor, cVer)));
                curAng += angInterval;
            }
        }
    }
}
