package com.tools.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
    public static String getEnvConstantsItem(String key) {
        String Path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + System.getProperty("env") + File.separator;
        Properties prop = new Properties();
        InputStream input = null;
        String propertyValue = "";
        try {
            input = new FileInputStream(Path + "environmentConstants.properties");
            prop.load(input);
            propertyValue = prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return propertyValue;

    }
}
