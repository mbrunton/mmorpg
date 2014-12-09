/**
 * Created by mitchellbrunton on 9/12/14.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private String host;
    private String port;
    private String playerDb;
    private Connection db;

    public DatabaseManager(GameSettings settings) throws DatabaseException {
        host = settings.getPlayerDbHost();
        port = settings.getPlayerDbPort();
        playerDb = settings.getPlayerDbName();

        String url = host + ":" + port + ":" + playerDb;
        try {
            db = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new DatabaseException("could not connect to database using url: " + url);
        }
    }
}
