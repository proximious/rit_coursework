import java.util.*;

public class EnvNull extends Env {

    // create an environment
    public EnvNull() {
    }

    // find the Val corresponding to a given id
    public Val applyEnv(String sym) {
        throw new PLCCException("no binding for "+sym);
    }

    public Binding lookup(String sym) {
        return null;
    }

    public Env add(Binding b) {
        throw new PLCCException("no bindings to add to");
    }

    public String toString() {
        return "\n";
    }
}