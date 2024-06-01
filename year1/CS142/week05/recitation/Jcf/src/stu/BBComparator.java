package stu;

import java.util.Comparator;

/**
 * CSCI-142 Computer Science 3 Recitation Exercise
 * 05-JCF1
 * BeanieBaby
 *
 * This is a custom comparator class that compares two BeanieBaby
 * objects.  One BeanyBaby is "less" than another if its type
 * is less, or if that is a tie, their name is alphabetically less.
 *
 * @author RIT CS
 */
public class BBComparator implements Comparator<BeanieBaby> {

    /**
     * The first BeanieBaby is "less" than the second if its type is less, or
     * if that is a tie, their name is alphabetically less.  If both are the
     * same the are equal. Otherwise the first is greater than the second.
     *
     * @param first the first beanie baby
     * @param second the second beanie baby
     * @return a value less than 0 if first is less than second, a value equal
     * to 0 if they are equal, or a value greater than 0 if the first
     * is greater than the second
     */
    @Override
    public int compare(BeanieBaby first, BeanieBaby second) {
        int result = first.getType().compareTo(second.getType());
        if( result == 0 ){
            
        }
        return result;
    }
}