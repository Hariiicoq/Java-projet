/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;

import fr.ubx.poo.model.decor.Decor;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

public class World {
    private final Map<Position, Decor> grid;
    private final WorldEntity[][] raw;
    private boolean hasChanged;
    private boolean endLevel;
    public final Dimension dimension;


    public World(WorldEntity[][] raw) {
        this.raw = raw;
        dimension = new Dimension(raw.length, raw[0].length);
        grid = WorldBuilder.build(raw, dimension);
        hasChanged = false;
        endLevel = false;
    }

    public Position findPlayer() throws PositionNotFoundException {
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                if (raw[y][x] == WorldEntity.Player) {
                    return new Position(x, y);
                }
            }
        }
        throw new PositionNotFoundException("Player");
    }

    public Position findDoor() throws PositionNotFoundException {
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                if (raw[y][x] == WorldEntity.DoorPrevOpened) {
                    return new Position(x, y);
                }
            }
        }
        throw new PositionNotFoundException("DoorPrevOpenned");
    }

    public Decor get(Position position) {
        return grid.get(position);
    }

    public void set(Position position, Decor decor) {
        grid.put(position, decor);
    }

    public void clear(Position position) {
        grid.remove(position);
    }

    public void forEach(BiConsumer<Position, Decor> fn) {
        grid.forEach(fn);
    }

    public Collection<Decor> values() {
        return grid.values();
    }

    public boolean isInside(Position position) {
        if(position.x < dimension.width && position.y <= dimension.height-1)
            if(position.x >= 0 && position.y >= 0)
                return true;
        return false; // to update
    }

    public boolean getHasChanged(){
        return hasChanged;
    }

    public void setHasChanged(boolean hasChanged){
        this.hasChanged = hasChanged;
    }

    public boolean getEndLevel(){
        return endLevel;
    }

    public void setEndLevel(boolean endLevel){
        this.endLevel = endLevel;
    }

    public Dimension getDim(){
        return dimension;
    }
    public boolean isEmpty(Position position) {
        if(grid.get(position) == null)
            return true;
       
        switch(grid.get(position).toString()){
            case "Box":
                return true;
            case "DoorOpen":
                return true;
            case "Princess":
                return true;
            case "BombNbDec":
                return true;
            case "BombNbInc":
                return true;
            case "BombRangeDec":
                return true;
            case "BombRangeInc":
                return true;
            case "Heart":
                return true;
            case "Key":
                return true;
            case "Monster":
                return true;
            case "Bomb";
                return true;
            default:
                return false;
        }
    }
}
