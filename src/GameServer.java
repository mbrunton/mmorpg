/**
 * Created by mitchellbrunton on 2/12/14.
 */

import com.json.*;
import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class GameServer {

    public static final String GAME_SETTINGS_JSON_PATH = "/Users/mitchellbrunton/IdeaProjects/mmorpg/config/settings.json";

    public static void main(String args[]) {
        // handle any command line flags supplied

        // parse settings json
        GameSettings settings = getGameSettings();

        // connect with state and player dbs

        // check state db for saved world state

        // load saved world state, or start a new one if absent

        // setup connection manager
            // setup active player table

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

    private static GameSettings getGameSettings() {
        JsonParserFactory jsonParserFactory = JsonParserFactory.getInstance();
        JSONParser jsonParser = jsonParserFactory.newJsonParser();

        String settingsJsonString = null;
        try {
            settingsJsonString = new Scanner(new File(GAME_SETTINGS_JSON_PATH)).useDelimiter("\\Z").next();
        } catch (IOException e) {
            return null;
        }

        Map settingsMap = jsonParser.parseJson(settingsJsonString);

        GameSettings settings = GameSettings.getInstance()
                                            .setName((String)settingsMap.get("name"));

        return settings;
    }
}
