/**
 * Created by mitchellbrunton on 9/12/14.
 *
 * Thrown when there is an issue with the settings.json file, such
 * as an expected key missing
 */
public class SettingsException extends Exception {

    public SettingsException() {
        // empty
    }

    public SettingsException(String message) {
        super(message);
    }
}
