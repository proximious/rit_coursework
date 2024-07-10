import java.util.*;

public class ProcVal extends Val {

    public Formals formals;
    public Exp body;
    public Env env;

    public ProcVal(Formals formals, Exp body, Env env) {
        this.formals = formals;
        this.body = body;
        this.env = env;
    }

    public Val apply(List<Val> args, Env e) {
        // bind the formals to the arguments
        if (formals.varList.size() != args.size())
            throw new PLCCException("formals/args number mismatch");
        Bindings bindings = new Bindings(formals.varList, args);
        // extend the captured environment with these bindings
        Env nenv = env.extendEnv(bindings);
        // and evaluate the body in this new environment
        return body.eval(nenv);
    }

    public String toString() {
        return "proc";
    }
}
