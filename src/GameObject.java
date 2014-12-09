/**
 * Created by mitchellbrunton on 10/12/14.
 */
public abstract class GameObject {
    private String name;

    public GameObject(String name) {
        this.name = name;
    }

    /**
     * This isn't called getName since I want it to look more
     * like enum's name method.
     */
    public String name() {
        return this.name();
    }
}
