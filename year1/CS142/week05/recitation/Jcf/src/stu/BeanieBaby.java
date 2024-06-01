package stu;

/**
 * Beanie Babies are a line of stuffed animals that became a major fad in the
 * 90's.  They were invented by Ty Inc., and many of the rarer ones still hold
 * a great value to some collectors.
 *
 * @author RIT CS
 */
public class BeanieBaby implements Comparable<BeanieBaby> {
    /** the type of babies we are dealing with */
    public enum Type {
        BAT,
        BEAR,
        DUCK,
        ELEPHANT,
    }

    /** the name of the baby */
    private String name;
    /** the year the baby was incepted */
    private int year;
    /** the type of baby */
    private Type type;

    /**
     * Create a new beanie baby.
     *
     * @param name their name
     * @param year their year incepted
     * @param type their type
     */
    public BeanieBaby(String name, int year, Type type) {
        this.name = name;
        this.year = year;
        this.type = type;
    }

    /**
     * Get the name of the beanie baby.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the year the beanie baby was incepted.
     *
     * @return year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Get the type of beanie baby.
     *
     * @return type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Just the name of the beanie baby is returned as a string
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Two beanie babies are equal if they have the same name, were incepted
     * in the same year, and are the same type
     *
     * @param other the other beanie baby to compare equality with
     * @return whether they are equal or not
     */
    @Override
    public boolean equals(Object other) {
        // TODO
        return false;
    }

    /**
     * Beanie babies are natural compared first by year in ascending order,
     * and second alphabetically by name if there is a tie.
     *
     * @param other the beanie baby to compare ordering with
     * @return a value less than 0 if this beanie baby is less than other,
     * 0 if they are equal, or a value greater than 0 if this beanie
     * baby is greater than other
     */
    @Override
    public int compareTo(BeanieBaby other) {
        // TODO
        return 0;
    }

    /**
     * The hash code of a beanie baby is the sum of the hash code of the name,
     * the year, and the hash code of the type.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        // TODO - change the next line to do the right thing
        return (int) (Math.random() * 10000000);
    }
}