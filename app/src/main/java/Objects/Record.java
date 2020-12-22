package Objects;


public class Record {
    private String name = "";
    private int score = 0;
    private Location location;

    public Record() { }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Record(String name, int score , Location location) {
        this.name = name;
        this.score = score;
        this.location =location;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }

    @Override
    public String toString() {
        return  name  + " score:" + score;// + " location:" + location.toString() ;
    }
}
