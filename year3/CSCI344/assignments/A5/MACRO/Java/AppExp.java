//AppExp:top//
//AppExp:import//
import java.util.*;

// <exp>:AppExp ::= DOT <exp> LPAREN <rands> RPAREN
public class AppExp extends Exp /*AppExp:class*/ {

    public static final String $className = "AppExp";
    public static final String $ruleString =
        "<exp>:AppExp ::= DOT <exp> LPAREN <rands> RPAREN";

    public Exp exp;
    public Rands rands;
    public AppExp(Exp exp, Rands rands) {
//AppExp:init//
        this.exp = exp;
        this.rands = rands;
    }

    public static AppExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:AppExp", scn$.lno);
        scn$.match(Token.Match.DOT, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        scn$.match(Token.Match.LPAREN, trace$);
        Rands rands = Rands.parse(scn$, trace$);
        scn$.match(Token.Match.RPAREN, trace$);
        return new AppExp(exp, rands);
    }


    public Val eval(Env env) {
        Val v = exp.eval(env);
        List<Val> args = rands.evalRands(env);
        return v.apply(args, env);
    }

    public String toString() {
        return " ... AppExp ...";
    }


//AppExp//
}
