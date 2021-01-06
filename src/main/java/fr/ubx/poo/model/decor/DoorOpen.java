package fr.ubx.poo.model.decor;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.World;

public class DoorOpen extends Decor {
    @Override
    public String toString() {
        return "DoorOpen";
    }

    public void updateDecor(Game game){
        World world = game.getWorld();
        world.setEndLevel(true);
    }
}
