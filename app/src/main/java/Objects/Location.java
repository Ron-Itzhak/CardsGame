package Objects;

public class Location {
    private double lat;
    private double lng;

    public Location() { }

    public Location(double lat, double lng) {
        this.lat=lat;
        this.lng=lng;

    }

    public double getLon() {
        return lng;
    }

    public void setLon(double lng) {
        this.lng = lng;
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
                ", lon-" + lng ;
    }
}
