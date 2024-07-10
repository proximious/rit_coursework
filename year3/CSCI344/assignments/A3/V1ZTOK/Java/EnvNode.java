import java.util.*;

public class EnvNode extends Env {

    public Bindings bindings; // list of local bindings
    public Env env;           // next set of bindings

    // create an environment
    public EnvNode(Bindings bindings, Env env) {
        this.bindings = bindings;
        this.env = env;
    }

    public Binding lookup(String sym) {
        return bindings.lookup(sym);
    }

    public Val applyEnv(String sym) {
        Binding b = bindings.lookup(sym);
        if (b == null)
            return env.applyEnv(sym);
        return b.val;
    }

    public Env add(Binding b) {
        bindings.add(b);
        return this;
    }

    public String toString() {
        return bindings.toString() + "----\n" + env;
    }

}