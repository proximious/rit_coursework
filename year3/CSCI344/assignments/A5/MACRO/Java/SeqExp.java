//SeqExp:top//
//SeqExp:import//
import java.util.*;

// <exp>:SeqExp ::= LBRACE <exp> <seqExps> RBRACE
public class SeqExp extends Exp /*SeqExp:class*/ {

    public static final String $className = "SeqExp";
    public static final String $ruleString =
        "<exp>:SeqExp ::= LBRACE <exp> <seqExps> RBRACE";

    public Exp exp;
    public SeqExps seqExps;
    public SeqExp(Exp exp, SeqExps seqExps) {
//SeqExp:init//
        this.exp = exp;
        this.seqExps = seqExps;
    }

    public static SeqExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:SeqExp", scn$.lno);
        scn$.match(Token.Match.LBRACE, trace$);
        Exp exp = Exp.parse(scn$, trace$);
        SeqExps seqExps = SeqExps.parse(scn$, trace$);
        scn$.match(Token.Match.RBRACE, trace$);
        return new SeqExp(exp, seqExps);
    }

    public Val eval(Env env) {
        Val v = exp.eval(env);
        for (Exp e : seqExps.expList)
            v = e.eval(env);
        return v;
    }

    public String toString() {
        return " ... SeqExp ... ";
    }


//SeqExp//
}
