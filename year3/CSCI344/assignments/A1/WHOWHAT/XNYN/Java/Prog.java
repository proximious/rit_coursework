//Prog:top//
//Prog:import//
import java.util.*;

// <prog> ::= <xnyn> HASH
public class Prog extends _Start /*Prog:class*/ {

    public static final String $className = "Prog";
    public static final String $ruleString =
        "<prog> ::= <xnyn> HASH";

    public Xnyn xnyn;

    public Prog() { } // dummy constructor

    public Prog(Xnyn xnyn) {
//Prog:init//
        this.xnyn = xnyn;
    }

    public static Prog parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prog>", scn$.lno);
        Xnyn xnyn = Xnyn.parse(scn$, trace$);
        scn$.match(Token.Match.HASH, trace$);
        return new Prog(xnyn);
    }

//Prog//
}
