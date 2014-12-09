import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitchellbrunton on 9/12/14.
 */

public class Player {

    public enum Race {GNOME, WIZARD}

    private String username;
    private String passwordHash;
    private Race race;

    private Position spawnPosition;

    private MeleeWeapon meleeWeapon;
    private RangedWeapon rangedWeapon;
    private List<InventoryItem> inventory;
    private List<Integer> buildingIds;

    private Timestamp dateJoined;

    public Player(String username, String passwordHash, Race race, int inventorySize) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.race = race;

        this.inventory = new ArrayList<InventoryItem>(inventorySize);
        for (int i = 0; i < inventorySize; i++) {
            inventory.set(i, null);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Position getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(Position spawnPosition) {
        this.spawnPosition = spawnPosition;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public RangedWeapon getRangedWeapon() {
        return rangedWeapon;
    }

    public void setRangedWeapon(RangedWeapon rangedWeapon) {
        this.rangedWeapon = rangedWeapon;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    public List<Integer> getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(List<Integer> buildingIds) {
        this.buildingIds = buildingIds;
    }

    public Timestamp getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }
}
