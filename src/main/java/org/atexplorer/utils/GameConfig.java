package org.atexplorer.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class GameConfig {
    private final Properties properties = new Properties();

    public void loadConfig(String filePath){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream input = loader.getResourceAsStream(filePath)){
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        properties.setProperty("tileSize", setTileSize());
        properties.setProperty("screenWidth", setScreenWidth());
        properties.setProperty("screenHeight", setScreenHeight());

    }

    public int getTileSize(){
        return Integer.parseInt(properties.getProperty("tileSize"));
    }

    private String setTileSize(){
        return String.valueOf(Integer.parseInt(properties.getProperty("originalTileSize")) *
                Integer.parseInt(properties.getProperty("scale")));
    }

    public int getScreenWidth(){
        return Integer.parseInt(properties.getProperty("screenWidth"));
    }

    private String setScreenWidth(){
        return String.valueOf(Integer.parseInt(properties.getProperty("tileSize")) *
                Integer.parseInt(properties.getProperty("maxScreenColumn")));
    }

    public int getScreenHeight(){
        return Integer.parseInt(properties.getProperty("screenHeight"));
    }

    private String setScreenHeight(){
        return String.valueOf(Integer.parseInt(properties.getProperty("tileSize")) *
                Integer.parseInt(properties.getProperty("maxScreenRow")));
    }

    public int getFps(){
        return Integer.parseInt(properties.getProperty("fps"));
    }
}
