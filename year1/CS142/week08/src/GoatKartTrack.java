import java.util.*;

public class GoatKartTrack implements Runnable{

    private Object troll;
    private List<Runnable> karts;
    public final static int MAX_RACERS = 4;

    public GoatKartTrack(){
        karts = new ArrayList<>();
        troll = new Object();
    }

    @Override
    public void run() {
        for(int i = 0; i < MAX_RACERS; i++){
            GoatKart kart = new GoatKart ("Goat " + (i+1), troll);
            Thread kartThread = new Thread(kart);
            kartThread.start();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {}

        System.out.println("Troll started the race");
    }

    public static void main(String[] args) {
        GoatKartTrack track = new GoatKartTrack();
        Thread trackThread = new Thread(track);
        trackThread.start();
    }
}
