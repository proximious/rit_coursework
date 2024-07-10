public class Binding {

    public String id;
    public Val val;

    public Binding(String id, Val val) {
        this.id = id;
        this.val = val;
    }

    public String toString() {
        return "[" + id + ":" + val.toString() + "]";
    }

}