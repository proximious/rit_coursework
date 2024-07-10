import java.util.*;

public class Bindings {

    public List<Binding> bindingList;

    // create an empty list of bindings
    public Bindings() {
        bindingList = new ArrayList<Binding>();
    }

    // create an empty list of bindings with the given capacity
    public Bindings(int capacity) {
        bindingList = new ArrayList<Binding>(capacity);
    }

    public Bindings(List<Binding> bindingList) {
        this.bindingList = bindingList;
    }

    public Binding lookup(String sym) {
        for (Binding b: bindingList)
            if (sym.equals(b.id))
                return b;
        return null;
    }

    // idList is a list of Tokens/Strings (whatever has a toString())
    // valList is a list of Vals
    public Bindings(List<?> idList, List<Val> valList) {
        if (idList.size() != valList.size())
            throw new PLCCException("List sizes mismatch");
        bindingList = new ArrayList<Binding>(idList.size());
        Iterator<?> idIterator = idList.iterator();
        Iterator<Val> valIterator = valList.iterator();
        while (idIterator.hasNext()) {
            String s = idIterator.next().toString();
            Val v = valIterator.next();
            this.add(new Binding(s, v));
        }
    }

    public void add(Binding b) {
        bindingList.add(b);
    }

    public void add(String s, Val v) {
        add(new Binding(s, v));
    }

    public int size() {
        return bindingList.size();
    }

    public String toString() {
        String s = "";
        for (Binding b : bindingList)
            s += b + "\n";
        return s;
    }
}