//LetExp:top//
//LetExp:import//
import java.util.*;

// <exp>:LetExp ::= LET <letDecls> IN <exp>
public class LetExp extends Exp /*LetExp:class*/ {

    public static final String $className = "LetExp";
    public static final String $ruleString =
        "<exp>:LetExp ::= LET <letDecls> IN <exp>";

    public LetDecls letDecls;
    public Exp exp;
    public LetExp(LetDecls letDecls, Exp exp) {
//LetExp:init//
        this.letDecls = letDecls;
        this.exp = exp;
    }

    public static LetExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:LetExp", scn$.lno);
        scn$.match(Token.Match.LET, trace$);
        LetDecls letDecls = LetDecls.parse(scn$, trace$);
        scn$.match(Token.Match.IN, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        return new LetExp(letDecls, exp);
    }

    public Val eval(Env env) {
        Env nenv = letDecls.addBindings(env);
        return exp.eval(nenv);
    }

    public String toString() {
        return "... LetExp ...";
    }

//LetExp//
}
