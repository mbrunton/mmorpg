/**
 * Created by mitchellbrunton on 2/12/14.
 */

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class GameServer {

    public static final String GAME_SETTINGS_JSON_PATH =
            "/Users/mitchellbrunton/IdeaProjects/mmorpg/config/settings.json";

    public static void main(String args[])
            throws SettingsException, DatabaseException {
        // DEBUGGING: dummy player
        Player dummyPlayer = new Player("username", "hash", Player.Race.GNOME, new Position(), new MeleeWeapon("SWORD"),
                                        new RangedWeapon("BOW"), new ArrayList<InventoryItem>(),
                                        new ArrayList<Integer>(), new Timestamp(System.currentTimeMillis()));

        // handle any command line flags supplied

        // parse settings json
        GameSettings settings = getGameSettings();

        // connect with state and player dbs
        DatabaseManager databaseManager = new DatabaseManager(settings);

        // check state db for saved world state

        // load saved world state, or start a new one if absent

        // setup active player table
        ActivePlayerTable activePlayerTable = new ActivePlayerTable();
        activePlayerTable.addPlayer(dummyPlayer); // DEBUGGING

        // setup connection manager
        ConnectionManager connectionManager = new ConnectionManager(databaseManager, activePlayerTable);

        // setup input manager

        // setup output manager

        /* repeat:
                receive state delta from input manager
                update world state
                dispatch local state deltas to active players
         */

        // periodically write world state back to state db
        // periodically write player state updates to player db
    }

    private static GameSettings getGameSettings() throws SettingsException {
        JsonParserFactory jsonParserFactory = JsonParserFactory.getInstance();
        JSONParser jsonParser = jsonParserFactory.newJsonParser();

        String settingsJsonString = null;
        try {
            settingsJsonString = new Scanner(new File(GAME_SETTINGS_JSON_PATH))
                                        .useDelimiter("\\Z").next();
        } catch (IOException e) {
            throw new SettingsException("unable to read settings file: " + GAME_SETTINGS_JSON_PATH);
        }

        Map settingsMap = jsonParser.parseJson(settingsJsonString);
        GameSettings settings = new GameSettings(settingsMap);

        return settings;
    }
}
