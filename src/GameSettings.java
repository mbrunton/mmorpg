import java.util.Map;

/**
 * Created by mitchellbrunton on 8/12/14.
 */
public class GameSettings {

    private String name;
    private String playerDbHost;
    private String playerDbPort;
    private String playerDbName;
    private String playerTableName;
    private String[] races;
    private String[] meleeWeapons;
    private String[] rangedWeapons;
    private String[] inventoryItems;

    public static final String DEFAULT_NAME = "mmorpg";


    public GameSettings(Map settingsMap) throws SettingsException {
        setName((String)settingsMap.get("name"));
        setPlayerDbHost((String)settingsMap.get("playerDbHost"));
        setPlayerDbPort((String)settingsMap.get("playerDbPort"));
        setPlayerDbName((String)settingsMap.get("playerDbName"));
        setPlayerTableName((String)settingsMap.get("playerTableName"));
        setRaces((String[]) settingsMap.get("races"));
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = DEFAULT_NAME;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setPlayerDbHost(String host) throws SettingsException {
        if (host == null) {
            throw new SettingsException("settings doesn't contain player db host");
        }
        this.playerDbHost = host;
    }

    public String getPlayerDbHost() {
        return this.playerDbHost;
    }

    public void setPlayerDbPort(String port) throws SettingsException {
        if (port == null) {
            throw new SettingsException("settings doesn't contain player db port");
        }
        this.playerDbPort = port;
    }

    public String getPlayerDbPort() {
        return this.playerDbPort;
    }

    public void setPlayerDbName(String dbName) throws SettingsException {
        if (dbName == null) {
            throw new SettingsException("settings doesn't contain player db name");
        }
        this.playerDbName = dbName;
    }

    public String getPlayerDbName() {
        return this.playerDbName;
    }

    public void setPlayerTableName(String tableName) throws SettingsException {
        if (tableName == null) {
            throw new SettingsException("settings doesn't contain player table name");
        }
        this.playerTableName = tableName;
    }

    public String getPlayerTableName() {
        return this.playerTableName;
    }

    public String[] getRaces() {
        return this.races;
    }

    private void setRaces(String[] races) throws SettingsException {
        if (races == null) {
            throw new SettingsException("settings doesn't contain races");
        }
        this.races = races;
    }
}
