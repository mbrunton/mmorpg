/**
 * Created by mitchellbrunton on 2/12/14.
 */
public class GameServer {

    public static void main(String args[]) {
        // handle any command line flags supplied

        // connect with state and player dbs

        // check state db for saved world state

        // load saved world state, or start a new one if absent

        // setup active player table

        // setup connection manager

        // setup input manager

        // setup update manager

        /* repeat:
                receive state delta from input manager
                update world state
                dispatch local state deltas to active players
         */

        // periodically write world state back to state db
        // periodically write player state updates to player db
    }
}
