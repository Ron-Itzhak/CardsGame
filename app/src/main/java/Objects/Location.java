package Objects;

public class Location {
    private double lat;
    private double lon;

    public Location() { }

    public Location(double lat, double lon) {
        this.lat=lat;
        this.lon=lon;

    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Location:" +
                "lat-" + lat +
                ", lon-" + lon ;
    }
}
