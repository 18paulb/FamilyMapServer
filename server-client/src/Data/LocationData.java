package Data;

public class LocationData {
    private Location[] data;

    public LocationData(Location[] data) {
        this.data = data;
    }

    public LocationData() {}

    public Location[] getData() {
        return data;
    }

    public void setData(Location[] data) {
        this.data = data;
    }
}
