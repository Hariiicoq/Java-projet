package fr.ubx.poo.model.decor;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class BombRangeInc extends Decor {
    @Override
    public String toString() {
        return "BombRangeInc";
    }

    public void updateDecor(Game game){
        Player player = game.getPlayer();
        World world = game.getWorld();
        player.setRange(player.getRange()+1);
        world.setHasChanged(true);
        world.clear(player.getPosition());
    }
}