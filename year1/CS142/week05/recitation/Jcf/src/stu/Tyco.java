package stu;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * CSCI-142 Computer Science 3 Recitation Exercise
 * 05-JCF1
 * BeanieBaby
 *
 * Ty Inc. has made a JUnit test suite to make sure the BeanieBaby's
 * they produce can be used with the various JCF collection classes
 * correctly.
 *
 * @author RIT CS
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tyco {
    /** the list of beanie babies */
    private List<BeanieBaby> babyList;
    /** Garcia the bear */
    private BeanieBaby garcia;
    /** Princess Diana the bear */
    private BeanieBaby diana;
    /** Quacker the duck */
    private BeanieBaby quacker;
    /** Sakura the bear */
    private BeanieBaby sakura;
    /** Peanut the elephant */
    private BeanieBaby peanut;
    /** Batty the bat */
    private BeanieBaby batty;

    /**
     * This method runs first, before each of the individual tests below.
     * It sets up the original list of beanie babies to work with.
     */
    @Before
    public void init() {
        // create the list and add the different beanie babies
        this.babyList = new ArrayList<>();
        this.babyList.add(new BeanieBaby("Garcia", 1995, BeanieBaby.Type.BEAR));
        this.babyList.add(new BeanieBaby("Princess Diana", 1997, BeanieBaby.Type.BEAR));
        this.babyList.add(new BeanieBaby("Quacker", 1994, BeanieBaby.Type.DUCK));
        this.babyList.add(new BeanieBaby("Sakura", 2000, BeanieBaby.Type.BEAR));
        this.babyList.add(new BeanieBaby("Peanut", 1995, BeanieBaby.Type.ELEPHANT));
        this.babyList.add(new BeanieBaby("Batty", 1996, BeanieBaby.Type.BAT));

        // get references to each of the babies so we can reference by name when needed
        this.garcia = this.babyList.get(0);
        this.diana = this.babyList.get(1);
        this.quacker = this.babyList.get(2);
        this.sakura = this.babyList.get(3);
        this.peanut = this.babyList.get(4);
        this.batty = this.babyList.get(5);
    }

    /**
     * This test makes sure the List contains() method works properly with
     * BeanieBaby's.
     *
     * @see java.util.List#contains(Object)
     */
    @Test
    public void A_testContains() {
        // make sure all the beanie babies in the list can be found
        for (BeanieBaby baby : this.babyList) {
            assertTrue(this.babyList.contains(baby));
        }

        // check that an exactly matching new beanie baby object can be found
        assertTrue(this.babyList.contains(new BeanieBaby("Batty", 1996, BeanieBaby.Type.BAT)));

        // make sure new beanie baby objects with slight alterations cannot be found
        assertFalse(this.babyList.contains(new BeanieBaby("Batty", 1997, BeanieBaby.Type.BAT)));
        assertFalse(this.babyList.contains(new BeanieBaby("Batty", 1996, BeanieBaby.Type.ELEPHANT)));
        assertFalse(this.babyList.contains(new BeanieBaby("Wallace", 1999, BeanieBaby.Type.BEAR)));
    }

    /**
     * This test makes sure the BeanieBaby's can be naturally ordered
     * incrementally by year and then alphabetically by name if a tie.
     *
     *  @see java.lang.Comparable
     */
    @Test
    public void B_testComparable() {
        // create a tree set based on the natural comparison, Comparable's compareTo()
        Set<BeanieBaby> babySet = new TreeSet<>(this.babyList);

        // verify the order is incrementally by year and then alphabetically by name if a tie
        assertEquals("[Quacker, Garcia, Peanut, Batty, Princess Diana, Sakura]",
                babySet.toString());
    }

    /**
     * This test makes sure the BeanieBaby's can be ordered differently than
     * their natural order.  Here we want them to order by type (the order
     * the enums are listed in which is alphabetical), and then alphabetically
     * by name if a tie.
     *
     * @see java.util.Comparator
     */
    @Test
    public void C_testComparator() {
        // create a tree set using a Comparator's compare()
        Set<BeanieBaby> babySet = new TreeSet<>(new BBComparator());
        // add all the elements to the set
        babySet.addAll(this.babyList);

        // verify the order is by type (alphabetically) and then alphabetically by name if a tie
        assertEquals("[Batty, Garcia, Princess Diana, Sakura, Quacker, Peanut]",
                babySet.toString());
    }

    /**
     * This test makes sure the BeanieBaby's can be stored in a hashable set.
     * If two "equal" but different BeanieBaby objects are added, only the
     * first should remain in the set.
     *
     * @see Object#hashCode()
     */
    @Test
    public void D_testHashSet() {
        Set<BeanieBaby> babySet = new HashSet<>(this.babyList);

        // add an exact duplicate beanie baby, Garcia
        babySet.add(new BeanieBaby("Garcia", 1995, BeanieBaby.Type.BEAR));

        // add all the names of the beanie babies into a list
        List<String> babyNames = new ArrayList<>();
        for (BeanieBaby baby : babySet) {
            babyNames.add(baby.getName());
        }

        // verify that only one of each beanie baby is in the list
        for (BeanieBaby baby : this.babyList) {
            assertEquals(1, Collections.frequency(babyNames, baby.getName()));
        }
    }

    /**
     * This test makes sure BeanieBaby objects can be stored in a hashable
     * map and set, so that they are ordered based on their street market
     * value from lowest to highest.  Note, this only works if each
     * BeanieBaby has a unique value.
     */
    @Test
    public void E_testMaps() {
        Map<BeanieBaby, Double> babyMap = new HashMap<>();

        // add the beanie babies to the hashmap with their associated value
        babyMap.put(this.garcia, 130.99);
        babyMap.put(this.diana, 10000.50);
        babyMap.put(this.quacker, 2700.30);
        babyMap.put(this.sakura, 50.10);
        babyMap.put(this.peanut, 670.75);
        babyMap.put(this.batty, 50.50);

        // "invert" the hash map by creating a tree map where the keys are the
        // babies values, and the values are the babies themselves
        Map<Double, BeanieBaby> valueMap = new TreeMap<>();

        // TODO

        // verify the beanie babies are ordered from lowest to highest value
        assertEquals("[Sakura, Batty, Garcia, Peanut, Quacker, Princess Diana]",
                valueMap.values().toString());
    }
}