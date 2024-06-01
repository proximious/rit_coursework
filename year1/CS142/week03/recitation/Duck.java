
public class Duck implements Animal, Swimmer, Flyer{
    final static double FLY_SPEED_MS = 22.352;
    final static double RUN_SPEED_MS = 13.94765;
    final static double SWIM_SPEED_MS = 26.8224;

    private double wingSpan;
    private int happiness;

    public Duck(double wingSpan){
        this.wingSpan = wingSpan;
        this.happiness = 0;
    }


    @Override
    public double run(int seconds) {
        return (seconds * RUN_SPEED_MS) + this.wingSpan;
    }

    @Override
    public double dive(int minutes){
        return (minutes * 60 * SWIM_SPEED_MS);
    }

    @Override
    public double fly(int seconds){
        return (FLY_SPEED_MS * seconds * wingSpan);
    }

    @Override
    public String speak() {
        return toString() + " says, \"quack!, quack!\"";
    }

    @Override
    public String toString() {
        return "Duck{" +
                "wingSpan=" + this.wingSpan +
                ", happiness=" + this.happiness +
                '}';
    }

    public double getHappiness() {
        return this.happiness;
    }

    public void pet(int seconds){
        happiness += (seconds * this.wingSpan);
    }
}
