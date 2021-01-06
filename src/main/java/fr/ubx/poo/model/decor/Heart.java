package fr.ubx.poo.model.decor;
import fr.ubx.poo.game.Game;
import fr.ubx.poo.game.World;
import fr.ubx.poo.model.go.character.Player;

public class Heart extends Decor {
    @Override
    public String toString() {
        return "Heart";
    }

    public void updateDecor(Game game){
        Player player = game.getPlayer();
        int lives = player.getLives();
        World world = game.getWorld();
        lives +=1;
        player.setLives(lives);
        world.setHasChanged(true);
        world.clear(player.getPosition());
    }
}
