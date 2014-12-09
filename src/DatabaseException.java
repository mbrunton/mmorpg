/**
 * Created by mitchellbrunton on 9/12/14.
 *
 * Thrown if we cannot connect to our underlying player (or other)
 * database. Separate from SQLException since we may change the backend.
 */
public class DatabaseException extends Exception {
    public DatabaseException() {
        // empty
    }
    public DatabaseException(String message) {
        super(message);
    }
}
