/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.ubx.poo.model.go.character.Player;

public class Game {

    private World world;
    private final Player player;
    private final String worldPath;
    public int initPlayerLives;
    private int level;
    private int levelMax;

    public Game(String worldPath) {
        level = 1;
        WorldFromFile wff = new WorldFromFile(worldPath,level);
        levelMax = wff.getNbMaxLevel();
        world = new World(wff.getWorldEntity());
        this.worldPath = worldPath;
        loadConfig(worldPath);
        Position positionPlayer = null;
        try {
            positionPlayer = world.findPlayer();
            player = new Player(this, positionPlayer);
        } catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public int getInitPlayerLives() {
        return initPlayerLives;
    }

    private void loadConfig(String path) {
        try (InputStream input = new FileInputStream(new File(path, "config.properties"))) {
            Properties prop = new Properties();
            // load the configuration file
            prop.load(input);
            initPlayerLives = Integer.parseInt(prop.getProperty("lives", "3"));
        } catch (IOException ex) {
            System.err.println("Error loading configuration");
        }
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getLevel(){
        return this.level;
    }

    public void nextLevel(){
        updateWorld();
        Position positionPlayer = null;
        try {
            positionPlayer = world.findDoor();
            player.setPosition(positionPlayer);
        } catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }

    }

    public void updateWorld(){
        if(level < levelMax)
            level+=1;
        WorldFromFile wff = new WorldFromFile(worldPath,level);
        world = new World(wff.getWorldEntity());
        System.out.println(world.getDim().height+";"+world.getDim().width);
    }   

}
