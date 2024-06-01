/**
 * All animals can run and speak.
 *
 * @author RIT CS
 */
public interface Animal {
    /**
     * Animal runs for a certain number of seconds and returns the distance
     * travelled.
     *
     * @param seconds number of seconds to run
     * @return distance travelled
     */
    double run(int seconds);

    /**
     * What the animal says when they speak.
     *
     * @return their message
     */
    String speak();
}
