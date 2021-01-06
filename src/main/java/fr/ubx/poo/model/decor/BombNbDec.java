package fr.ubx.poo.model.decor;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class BombNbDec extends Decor {
    @Override
    public String toString() {
        return "BombNbDec";
    }

    public void updateDecor(Game game){
        Player player = game.getPlayer();
        int bombs = player.getBombs();
        World world = game.getWorld();
        if(bombs > 1)
            bombs -=1;
        player.setBombs(bombs);
        world.setHasChanged(true);
        world.clear(player.getPosition());
    }
}