package fr.ubx.poo.game;

import fr.ubx.poo.model.decor.*;
import fr.ubx.poo.model.decor.Monster;
import java.util.Hashtable;
import java.util.Map;


public class WorldBuilder {
    private final Map<Position, Decor> grid = new Hashtable<>();

    private WorldBuilder() {
    }

    public static Map<Position, Decor> build(WorldEntity[][] raw, Dimension dimension) {
        WorldBuilder builder = new WorldBuilder();
        for (int x = 0; x < dimension.width; x++) {
            for (int y = 0; y < dimension.height; y++) {
                Position pos = new Position(x, y);
                Decor decor = processEntity(raw[y][x]);
                if (decor != null)
                    builder.grid.put(pos, decor);
            }
        }
        return builder.grid;
    }

    private static Decor processEntity(WorldEntity entity) {
        switch (entity) {
            case Stone:
                return new Stone();
            case Tree:
                return new Tree();
            case Box:
                return new Box();
            case Key:
                return new Key();
            case DoorNextClosed:
                return new DoorClose();
            case DoorPrevOpened:
                return new DoorOpenPrev();
            case DoorNextOpened:
                return new DoorOpen();
            case Princess:
                return new Princess();
            case BombNumberDec:
                return new BombNbDec();
            case BombNumberInc:
                return new BombNbInc();
            case BombRangeDec:
                return new BombRangeDec();
            case BombRangeInc:
                return new BombRangeInc();
            case Heart:
                return new Heart();
            case Monster:
                return new Monster();
            default:
                return null;
        }
    }
}
