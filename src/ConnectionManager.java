import java.sql.SQLException;

/**
 * Created by mitchellbrunton on 2/12/14.
 */
public class ConnectionManager {

    private final DatabaseManager databaseManager;
    private final ActivePlayerTable activePlayerTable;

    public ConnectionManager(DatabaseManager databaseManager, ActivePlayerTable activePlayerTable) {
        this.databaseManager = databaseManager;
        this.activePlayerTable = activePlayerTable;
    }

    public boolean signupPlayer(Player player) {
        boolean success = false;
        try {
            success = databaseManager.insertPlayer(player);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return success;
    }

    public boolean connectPlayer(String username, String passwordHash) {
        Player player = null;
        try {
            player = databaseManager.retrievePlayer(username, passwordHash);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (player != null) {
            activePlayerTable.addPlayer(player);
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePlayer(Player player) {
        boolean success = false;
        try {
            success = databaseManager.updatePlayer(player);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return success;
    }

    public boolean disconnectPlayer(String username) {
        boolean success = activePlayerTable.removePlayer(username);
        return success;
    }
}
