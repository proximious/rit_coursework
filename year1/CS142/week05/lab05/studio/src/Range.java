import java.lang.Iterable;

public interface Range extends Iterable <Integer> {

    int getValue(int index);
    int size();
}
