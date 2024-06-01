public class Duck extends Animal implements Swimmer, Flyer{
    final static double FLY_SPEED_MS = 22.352;
    final static double RUN_SPEED_MS = 13.94765;
    final static double SWIM_SPEED_MS = 26.8224;

    private double wingSpan;
    private int happiness;

    public Duck (double wingSpan, String name){
        super(name);
        this.wingSpan = wingSpan;
        happiness = 0;
    }

    public void pet(int seconds){
        happiness += (seconds * wingSpan);
    }

    public int getHappiness(){
        return this.happiness;
    }

    public void run(int seconds){
        travel((seconds * RUN_SPEED_MS) + wingSpan);
    }

    public String speak(){
        return toString() + " says, \"quack!, quack!\"";
    }

    public void dive(int minutes){}

    public int getFishEaten(){
        return
    }

    public void fly(int seconds){}

    @Override
    public String toString(){
        return super.toString() + "Duck{" +
                "wingSpan=" + this.wingSpan +
                ", happiness=" + this.happiness +
                "}}";
    }

}
