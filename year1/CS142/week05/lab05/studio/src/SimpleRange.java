import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SimpleRange implements Range {

    private int start;
    private int end;
    private int step;

    public SimpleRange(int start, int end, int step){
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator(this);
    }

    @Override
    public int getValue(int index) {
        return (step * (index + start));
    }

    @Override
    public int size() {
        return (end - start) / step;
    }

    public static Range range(int start, int end, int step){
        return new SimpleRange(start, end, step);
    }

    public static void main(String[] args) {
        for (int i: range(0, 20, 3)){
            System.out.println(i);
        }
    }
}
