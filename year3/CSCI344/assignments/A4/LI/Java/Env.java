import java.util.*;

public abstract class Env {

    public static final Env empty = new EnvNull();

    public static void checkDuplicates(List<Token> varList, String msg) {
        // throws an exeption if the varList has duplicate vars
        Set<String> varSet = new HashSet<String>(2*varList.size());
        for (Token var: varList) {
            String str = var.toString();
            if (varSet.contains(str)) {
                msg = "duplicate ID " + str + msg;
                throw new PLCCException("Semantic error",  msg);
            }
            varSet.add(str);
        }
    }

    public static void checkDuplicates(List<Token> varList) {
        checkDuplicates(varList, "");
    }

    public static Env initEnv() {
        // initial environment with no bindings
        return new EnvNode(new Bindings(), empty);
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