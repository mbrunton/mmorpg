/**
 * Created by mitchellbrunton on 8/12/14.
 */
public class GameSettings {
    private GameSettings() {}

    private String name;
    public static final String DEFAULT_NAME = "mmorpg";

    public static GameSettings getInstance() {
        return new GameSettings();
    }

    public GameSettings setName(String name) {
        this.name = name == null ? DEFAULT_NAME : name;
        return this;
    }

}
