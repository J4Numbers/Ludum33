package uk.co.m4numbers.ludum.fileio;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class Name - LudumFile
 * Package - uk.co.m4numbers.ludum.fileio
 * Desc of Class - ...
 * Author(s) - M. D. Ball
 * Last Mod: 20/08/2015
 */
public class LudumFile {

    public static JSONObject loadFile(File f) throws FileNotFoundException, JSONException {
        try {
            Scanner s = new Scanner(f);
            s.useDelimiter("\\Z");
            JSONObject j = new JSONObject(s.next());

            return j;
        } catch (FileNotFoundException fnf) {
            throw fnf;
        } catch (JSONException jse) {
            throw jse;
        }
    }

    public static JSONObject loadFile(String s) throws FileNotFoundException, JSONException {
        return loadFile(new File("files/"+s));
    }

}
