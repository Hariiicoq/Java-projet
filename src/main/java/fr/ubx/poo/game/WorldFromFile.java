package fr.ubx.poo.game;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileReader;


import fr.ubx.poo.game.WorldEntity;

public class WorldFromFile{ 
    private String prefix;
    private Dimension dimension;
    private WorldEntity[][] raw;
    private int actualLevel;
    private int nbMaxLevel;
    private String path;

    public WorldFromFile(String path,int level){
        this.path = path;
        final Properties prop = new Properties();
        InputStream input = null;

        this.actualLevel = level;
        try {

			input = new FileInputStream(path+"/config.properties");
            

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			this.prefix = prop.getProperty("prefix");
			this.nbMaxLevel = Integer.parseInt(prop.getProperty("levels"));
			

		} catch (final IOException ex) {
			System.err.println("Error in properties file ");
        }
        

        int width =0;
        int height =0;
        Dimension dim= new Dimension(0,0);
        int character;
        try {
            BufferedReader file = new BufferedReader(new FileReader(path+"/"+prefix+actualLevel+".txt"));
            while ((character = file.read()) > -1) {
                if((char)character == '\n')
                    height +=1;
                if (height == 0)
                    width +=1;
            }
            dim = new Dimension(height,width);
            this.dimension = dim;
        } catch (IOException e) {
            System.err.println("Error loading world from file :"+ prefix+actualLevel+".txt");
        }



        try {
            BufferedReader file = new BufferedReader(new FileReader(path+"/"+prefix+actualLevel+".txt"));
            
            this.raw = new WorldEntity[dim.height][dim.width];
            for(int i = 0; i<dim.height;i++){
                for(int j = 0; j<dim.width+1;j++){
                    if((character = file.read()) != -1)
                    switch(character){
                        case '\n':
                            break;
                        case '_' :  
                            raw[i][j] = WorldEntity.Empty;
                            break;  
                        case 'B' :
                            raw[i][j] = WorldEntity.Box;
                            break;
                        case 'H' :
                            raw[i][j] = WorldEntity.Heart;
                            break;
                        case 'K' :
                            raw[i][j] =WorldEntity.Key;
                            break;
                        case 'M' :
                            raw[i][j] =WorldEntity.Monster;
                            break;
                        case 'V' :
                            raw[i][j] =WorldEntity.DoorPrevOpened;
                            break;
                        case 'N' :
                            raw[i][j] =WorldEntity.DoorNextOpened;
                            break;
                        case 'n' :
                            raw[i][j] =WorldEntity.DoorNextClosed;
                            break;
                        case 'P' :
                            raw[i][j] =WorldEntity.Player;
                            break;
                        case 'S' :
                            raw[i][j] =WorldEntity.Stone;
                            break;
                        case 'T' :
                            raw[i][j] =WorldEntity.Tree;
                            break;
                        case 'W' :
                            raw[i][j] =WorldEntity.Princess;
                            break;
                        case '>' :
                            raw[i][j] =WorldEntity.BombRangeInc;
                            break;
                        case '<' :
                            raw[i][j] =WorldEntity.BombRangeDec;
                            break;
                        case '+' :
                            raw[i][j] =WorldEntity.BombNumberInc;
                            break;
                        case '-' :
                            raw[i][j] =WorldEntity.BombNumberDec;
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading world from file :"+ prefix+actualLevel+".txt");
        }
    }

 /*   public WorldFromFile(String path){
        this.path = path;
        final Properties prop = new Properties();
        InputStream input = null;

        try {

			input = new FileInputStream(path+"/config.properties");
            

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			this.prefix = prop.getProperty("prefix");
			this.nbMaxLevel = Integer.parseInt(prop.getProperty("levels"));
			

		} catch (final IOException ex) {
			System.err.println("Error in properties file ");
        }
        this.actualLevel = 1;

        int width =0;
        int height =0;
        Dimension dim= new Dimension(0,0);
        int character;
        try {
            BufferedReader file = new BufferedReader(new FileReader(path+"/"+prefix+actualLevel+".txt"));
            while ((character = file.read()) > -1) {
                if((char)character == '\n')
                    height +=1;
                if (height == 0)
                    width +=1;
            }
            dim = new Dimension(height,width);
            this.dimension = dim;
        } catch (IOException e) {
            System.err.println("Error loading world from file :"+ prefix+actualLevel+".txt");
        }



        try {
            BufferedReader file = new BufferedReader(new FileReader(path+"/"+prefix+actualLevel+".txt"));
            
            this.raw = new WorldEntity[dim.height][dim.width];
            for(int i = 0; i<dim.height;i++){
                for(int j = 0; j<dim.width+1;j++){
                    if((character = file.read()) != -1)
                    switch(character){
                        case '\n':
                            break;
                        case '_' :  
                            raw[i][j] = WorldEntity.Empty;
                            break;  
                        case 'B' :
                            raw[i][j] = WorldEntity.Box;
                            break;
                        case 'H' :
                            raw[i][j] = WorldEntity.Heart;
                            break;
                        case 'K' :
                            raw[i][j] =WorldEntity.Key;
                            break;
                        case 'M' :
                            raw[i][j] =WorldEntity.Monster;
                            break;
                        case 'V' :
                            raw[i][j] =WorldEntity.DoorPrevOpened;
                            break;
                        case 'N' :
                            raw[i][j] =WorldEntity.DoorNextOpened;
                            break;
                        case 'n' :
                            raw[i][j] =WorldEntity.DoorNextClosed;
                            break;
                        case 'P' :
                            raw[i][j] =WorldEntity.Player;
                            break;
                        case 'S' :
                            raw[i][j] =WorldEntity.Stone;
                            break;
                        case 'T' :
                            raw[i][j] =WorldEntity.Tree;
                            break;
                        case 'W' :
                            raw[i][j] =WorldEntity.Princess;
                            break;
                        case '>' :
                            raw[i][j] =WorldEntity.BombRangeInc;
                            break;
                        case '<' :
                            raw[i][j] =WorldEntity.BombRangeDec;
                            break;
                        case '+' :
                            raw[i][j] =WorldEntity.BombNumberInc;
                            break;
                        case '-' :
                            raw[i][j] =WorldEntity.BombNumberDec;
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading world from file :"+ prefix+actualLevel+".txt");
        }
    }*/

    public WorldEntity[][] getWorldEntity(){
        return raw;
    }

    public int getActualLevel(){
        return actualLevel;
    }

    public int getNbMaxLevel(){
        return nbMaxLevel;
    }

    /*public void setSizeOfLevel(){
        try {
            FileInputStream inputStream = new FileInputStream(path+"/"prefix+actualLevel+".txt");
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-16");
            int character;
            int width = 0;
            int height = 0;
            while ((character = reader.read()) != -1) {
                if(character == '\n')
                    height +=1;
                width +=1;
            }
            width = width/height;
            reader.close();
            this.dimension = new Dimension(height,width);
        } catch (IOException e) {
            System.err.println("Error loading world from file :"+ prefix+actualLevel".txt");
        }
    }
    public void setWorldEntity(){
        try {
            FileInputStream inputStream = new FileInputStream(prefix);
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-16");
            int character;
            Dimension dimension = new Dimension(getSizeOfLevel(prefix));
            this.raw = new WorldEntity[dimension.height][dimension.width];
            for(int i = 0; i<dimension.heigt;i++){
                for(int j = 0; j<dimension.width;j++){
                    if((character = reader.read()) != -1)
                        raw[i][j] = character; 
                }
            }
            reader.close();
            return ;
        } catch (IOException e) {
            System.err.println("Error loading world from file :"+ prefix+actualLevel".txt");
        }
    }*/



}