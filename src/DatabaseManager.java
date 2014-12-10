/**
 * Created by mitchellbrunton on 9/12/14.
 *
 *
 * player table:
create table players(
username varchar(20) primary key not null,
passwordHash varchar(20) not null,
race varchar(20) not null,
spawnPosition char(20),
meleeWeapon varchar(20) not null,
rangedWeapon varchar(20) not null,
inventoryItems varchar(20)[] not null,
buildingIds int[] not null,
dateJoined timestamp without time zone not null
);
 */

import com.sun.istack.internal.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String insertString = "INSERT INTO " + settings.getPlayerTableName();
        insertString += "(username, passwordHash, raceId, spawnPosition, meleeWeapon, ";
        insertString += "rangedWeapon, inventoryItems, buildingIds, dateJoined) VALUES (";
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

    public @Nullable Player retrievePlayer(String username, String passwordHashAttempt) throws SQLException {
        Statement statement = db.createStatement();
        String selectString = "SELECT * FROM " + settings.getPlayerTableName() + " WHERE username = " + username;
        ResultSet resultSet = statement.executeQuery(selectString);

        assert(resultSet.next()) : "no player found with username " + username;
        String passwordHash = resultSet.getString("passwordHash");
        if (!passwordHash.equals(passwordHashAttempt)) {
            return null;
        }

        Player.Race race = Player.Race.valueOf(resultSet.getString("race"));
        String spawnPositionString = resultSet.getString("spawnPosition");
        Position spawnPosition;
        if (spawnPositionString == null) {
            spawnPosition  = new Position();
        } else {
            spawnPosition = new Position(spawnPositionString);
        }
        String meleeWeaponName = resultSet.getString("meleeWeapon");
        MeleeWeapon meleeWeapon = new MeleeWeapon(meleeWeaponName);
        String rangedWeaponName = resultSet.getString("rangedWeapon");
        RangedWeapon rangedWeapon = new RangedWeapon(rangedWeaponName);

        String[] inventoryItemNames = (String[]) resultSet.getArray("inventoryItems").getArray();
        List<InventoryItem> inventory = new ArrayList<InventoryItem>();
        for (int i = 0; i < inventoryItemNames.length; i++) {
            inventory.add(new InventoryItem(inventoryItemNames[i]));
        }

        Integer[] buildingIdsArray = (Integer[]) resultSet.getArray("buildingIds").getArray();
        List<Integer> buildingIds = new ArrayList<Integer>();
        for (int i = 0; i < buildingIdsArray.length; i++) {
            buildingIds.add(buildingIdsArray[i]);
        }

        Timestamp dateJoined = resultSet.getTimestamp("dateJoined");

        return new Player(username, passwordHash, race, spawnPosition, meleeWeapon, rangedWeapon,
                            inventory, buildingIds, dateJoined);
    }

    public boolean updatePlayer(Player player) {
        return false;
    }
}
