package fr.ubx.poo.model.decor;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;


public class Key extends Decor {
    @Override
    public String toString() {
        return "Key";
    }

    public void updateDecor(Game game){
        Player player = game.getPlayer();
        int keys = player.getKeys();
        World world = game.getWorld();
        keys +=1;
        player.setKeys(keys);
        world.setHasChanged(true);
        world.clear(player.getPosition());
    }
}
