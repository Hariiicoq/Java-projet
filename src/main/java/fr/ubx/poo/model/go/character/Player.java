/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.model.go.character;

import javax.lang.model.util.ElementScanner6;

import fr.ubx.poo.game.Direction;
import fr.ubx.poo.game.Position;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.Movable;
import fr.ubx.poo.model.go.GameObject;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.model.decor.*;

public class Player extends GameObject implements Movable {

    private final boolean alive = true;
    Direction direction;
    private boolean moveRequested = false;
    private int lives = 1;
    private boolean winner;
    private int bombs = 1;
    private int range = 1;
    private int keys = 0;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.S;
        this.lives = game.getInitPlayerLives();
    }

    public int getLives() {
        return lives;
    }

    public int getBombs() {
        return bombs;
    }

    public int getRange() {
        return range;
    }

    public int getKeys() {
        return keys;
    }
    
    public void setLives(int lives){
        this.lives = lives;
    }

    public void setBombs(int bombs){
        this.bombs = bombs;
    }

    public void setRange(int range){
        this.range = range;
    }

    public void setKeys(int keys){
        this.keys = keys;
    }

    public Direction getDirection() {
        return direction;
    }

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
        }
        moveRequested = true;
    }

    public void akhiOpenTheDoor(){
        Position nextPos = direction.nextPosition(getPosition());
        World world = game.getWorld();
        if(world.get(nextPos).toString() == "DoorClose"){
             if(keys >= 1){
                keys -=1;
                world.setHasChanged(true);  
                world.set(nextPos,new DoorOpen());
            }
        }
    }

    public void dropBomb(){
        if (this.bombs >= 1) {
            this.bombs -= 1;
            Position pos = getPosition();
            World world = game.getWorld();
            world.setHasChanged(true);
            world.set(pos, new Bomb());
        }
        

    }

    @Override
    public boolean canMove(Direction direction) {
        if(game.getWorld().isInside(direction.nextPosition(getPosition())))
            if(game.getWorld().isEmpty(direction.nextPosition(getPosition())))
                return true;
        return false;
    }

    public void doMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        /*if(game.getWorld().isEmpty(nextPos))
            if(game.getWorld().get(nextPos) != null){
               /* switch(game.getWorld().get(nextPos).toString()){
                   case "Monster":
                        this.lives -= 1;
                        break;
                    case "Princess":
                        this.winner = true;
                        break;
                    case "Heart":
                        this.lives +=1;
                        break;
                    case "BombNbDec":
                        if(this.bombs > 1)
                            this.bombs -=1;
                        break;
                    case "BombNbInc":
                        this.bombs +=1;
                        break;
                    case "BombRangeDec":
                        if(this.range > 1)
                            this.range -=1;
                        break;    
                    case "BombRangeInc":
                        this.range +=1;
                        break;
                    case "Key":
                        this.keys += 1;
                        break;
                    case "DoorClose":
                        if(this.keys > 0)
                            this.keys -=1;
                        break;
                }
            }*/
        setPosition(nextPos);
    }

    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
                if(game.getWorld().get(getPosition()) != null)
                game.getWorld().get(getPosition()).updateDecor(game);
            }
        }
        moveRequested = false;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isAlive() {
        if(this.lives <= 0 )
            return !alive;
        return alive;
    }

}
