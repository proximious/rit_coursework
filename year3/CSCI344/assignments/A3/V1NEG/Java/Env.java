import java.util.*;

public abstract class Env {

    public static final Env empty = new EnvNull();

    public static Env initEnv() {
        // Create bindings for some symbols
        List<Binding> bindingList = Arrays.asList(
            new Binding("i", new IntVal(1)),
            new Binding("v", new IntVal(5)),
            new Binding("x", new IntVal(10)),
            new Binding("l", new IntVal(50)),
            new Binding("c", new IntVal(100)),
            new Binding("d", new IntVal(500)),
            new Binding("m", new IntVal(1000)));
        return new EnvNode(new Bindings(bindingList), empty);
    }

    public abstract Binding lookup(String sym); // only local bindings

    public abstract Val applyEnv(String sym);

    public Val applyEnv(Token tok) {
        return applyEnv(tok.toString());
    }

    public Env extendEnv(Bindings bindings) {
        return new EnvNode(bindings, this);
    }

    public abstract Env add(Binding b);

}