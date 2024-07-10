/**
 * @author Alex Iacob ai9388@rit.edu
 * @filename Location.java
 * 
 * File represents a location instance via latitute and longitute
 */
public class Location {
    private double lat;
    private double lon;

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

}
