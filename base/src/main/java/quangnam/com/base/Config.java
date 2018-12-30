/*
 * MIT License
 *
 * Copyright (c) 2017 Tran Quang Nam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package quangnam.com.base;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import quangnam.com.base.utils.Log;

/**
 * Created by quangnam on 4/18/16.
 */
public class Config {

    public static final boolean DEBUG = true;

    // Debug with fake json implement in each service. To test GUI in offlinemode, set it to true
    public static final boolean DEBUG_FAKE_DATA = false;

    public static final boolean USE_FABRIC = DEBUG;

    public static final String CONFIG_FILE = "config_debug.properties";
    public static final int LOG_STACK_SIZE = 20;
    private static Properties sProperties = new Properties();

    static void loadConfig() {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File config = new File(root, CONFIG_FILE);

        InputStream inputStream = null;
        try {
            if (!config.exists()) {
                config.createNewFile();
            }
            inputStream = new FileInputStream(config);
            sProperties.load(inputStream);
        } catch (IOException e) {
            Log.e("Couldn't load config file");
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Properties getProperties() {
        return sProperties;
    }

    public static void saveConfig() {
        File root = Environment.getExternalStorageDirectory();
        File config = new File(root, CONFIG_FILE);

        OutputStream outputStream = null;
        try {
            if (!config.exists())
                config.createNewFile();
            outputStream = new FileOutputStream(config);
            sProperties.store(outputStream, "Save config");
        } catch (IOException e) {
            Log.e("Couldn't save config file");
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
