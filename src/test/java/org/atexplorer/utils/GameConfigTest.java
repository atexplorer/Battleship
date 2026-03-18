package org.atexplorer.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameConfigTest {

    private static final GameConfig gc = new GameConfig();

    @BeforeAll
    static void setup(){
        gc.loadConfig("C:\\Users\\ethan\\Documents\\BattleshipGameJava\\Battleship\\src\\main\\resources\\board.properties");
    }

    @Test
    void getTileSize() {
        assertEquals(32, gc.getTileSize());
    }

    @Test
    void getScreenWidth() {
        assertEquals(1024, gc.getScreenWidth());
    }

    @Test
    void getScreenHeight() {
        assertEquals(768, gc.getScreenHeight());
    }

    @Test
    void getFps() {
        assertEquals(60, gc.getFps());
    }
}