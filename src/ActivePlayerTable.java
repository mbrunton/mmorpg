import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitchellbrunton on 10/12/14.
 */
public class ActivePlayerTable {

    private List<ActivePlayer> activePlayers;

    private class ActivePlayer {
        private Player player;
        private Position currentPosition;
        private Orientation currentOrientation;

        private ActivePlayer(Player player) {
            this.player = player;
        }

        private Player getPlayer() {
            return this.player;
        }
    }

    public ActivePlayerTable() {
        activePlayers = new ArrayList<ActivePlayer>();
    }

    public void addPlayer(Player player) {
        activePlayers.add(new ActivePlayer(player));
    }

    public boolean removePlayer(String username) {
        ActivePlayer toDelete = null;
        for (ActivePlayer activePlayer : activePlayers) {
            if (username.equals(activePlayer.getPlayer().getUsername())) {
                toDelete = activePlayer;
                break;
            }
        }

        if (toDelete != null) {
            activePlayers.remove(toDelete);
            return true;
        } else {
            return false;
        }
    }
}
