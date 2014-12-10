/**
 * Created by mitchellbrunton on 9/12/14.
 */
public class Position {

    private int x;
    private int z;

    public Position() {
        // TODO: default or random position
    }

    public Position(String dbString) {
        assert(dbString.length() % 2 == 0) : "dbString length must be even";
        // TODO
    }
    /**
     *
     * @return a string repr for storage in database
     */
    public String toDbString() {
        return null;
    }
}
