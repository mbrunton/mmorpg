/**
 * Created by mitchellbrunton on 9/12/14.
 *
 *
 * player table:
     create table players(
         username varchar(20) primary key not null,
         passwordHash varchar(20),
         race varchar(20) not null,
         spawnPosition char(20),
         meleeWeaponId int not null,
         rangedWeaponId int not null,
         inventoryItemIds int[] not null,
         buildingIds int[] not null,
         dateJoined timestamp without time zone not null,
     );
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    private String host;
    private String port;
    private String playerDb;
    private Connection db;
    private GameSettings settings;

    public DatabaseManager(GameSettings settings) throws DatabaseException {
        this.settings = settings;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("could not locate postgresql driver... " +
                                        "make sure it's included in class path");
        }

        host = settings.getPlayerDbHost();
        port = settings.getPlayerDbPort();
        playerDb = settings.getPlayerDbName();

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + playerDb;
        try {
            db = DriverManager.getConnection(url);
            if (db == null) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new DatabaseException("could not connect to database using url: " + url);
        }
    }

    public boolean insertPlayer(Player player) throws SQLException {
        Statement statement = db.createStatement();
        String insertString = "INSERT INTO PLAYERS ";
        insertString += "(username, passwordHash, raceId, spawnPosition, meleeWeaponId, ";
        insertString += "rangedWeaponId, inventoryItemIds, buildingIds, dateJoined) VALUES (";
        insertString += player.getUsername() + ", ";
        insertString += player.getPasswordHash() + ", ";
        insertString += player.getRace().name() + ", ";

        String spawnPositionString = player.getSpawnPosition().toDbString();
        insertString += spawnPositionString + ", ";

        insertString += player.getMeleeWeapon().name() + ", ";
        insertString += player.getRangedWeapon().name() + ", ";

        List<InventoryItem> inventory = player.getInventory();
        insertString += "[";
        int inventoryLength = inventory.size();
        for (int i = 0; i < inventoryLength; i++) {
            InventoryItem item = inventory.get(i);
            String itemName = item.name();
            insertString += itemName + ((i < inventoryLength - 1) ? ", " : "], ");
        }

        insertString += "[";
        List<Integer> buildingIds = player.getBuildingIds();
        int buildingIdsLength = buildingIds.size();
        for (int i = 0; i < buildingIdsLength; i++) {
            insertString += buildingIds.get(i).toString() + ((i < buildingIdsLength - 1) ? ", " : "], ");
        }

        insertString += player.getDateJoined().toString() + ");";

        boolean success = statement.execute(insertString);
        return success;
    }

    public Player retrievePlayer(String username) {
        return null;
    }

    public boolean updatePlayer(Player player) {
        return false;
    }
}
