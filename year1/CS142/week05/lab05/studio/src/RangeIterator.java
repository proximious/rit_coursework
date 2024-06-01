import java.util.Iterator;

public class RangeIterator implements Iterator < Integer > {

    private Range range;
    private int index;

    public RangeIterator(Range range){
        this.range = range;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < range.size();
    }

    @Override
    public Integer next() {
        int value = range.getValue(index);
        index++;
        return value;
    }

}
