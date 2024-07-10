//ProcExp:top//
//ProcExp:import//
import java.util.*;

// <exp>:ProcExp ::= <proc>
public class ProcExp extends Exp /*ProcExp:class*/ {

    public static final String $className = "ProcExp";
    public static final String $ruleString =
        "<exp>:ProcExp ::= <proc>";

    public Proc proc;
    public ProcExp(Proc proc) {
//ProcExp:init//
        this.proc = proc;
    }

    public static ProcExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:ProcExp", scn$.lno);
        Proc proc = Proc.parse(scn$, trace$);
        return new ProcExp(proc);
    }

    public Val eval(Env env) {
        return proc.makeClosure(env);
    }

//ProcExp//
}
