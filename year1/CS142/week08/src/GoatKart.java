import java.util.Random;

public class GoatKart implements Runnable{

    private String name;
    private Object troll;

    public GoatKart(String name, Object troll){
        this.name = name;
        this.troll = troll;
    }

    @Override
    public void run() {
        Random rand = new Random();

        System.out.println(name + " arrives at the starting line");

        synchronized (troll){
            troll.notifyAll();
        }
        int time = rand.nextInt(5) + 5;

        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException ignored) {}


        System.out.println(name + " crosses the finish line");
    }
}
