package fr.ubx.poo.model.decor;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class BombRangeDec extends Decor {
    @Override
    public String toString() {
        return "BombRangeDec";
    }

    public void updateDecor(Game game){
        Player player = game.getPlayer();
        int range = player.getRange();
        World world = game.getWorld();
        if(range > 1)
            range -=1;
        player.setRange(range);
        world.setHasChanged(true);
        world.clear(player.getPosition());
    }
}