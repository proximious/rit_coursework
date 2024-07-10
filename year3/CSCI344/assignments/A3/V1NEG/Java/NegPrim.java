//NegPrim:top//
//NegPrim:import//
import java.util.*;

// <prim>:NegPrim ::= NEGOP
public class NegPrim extends Prim /*NegPrim:class*/ {

    public static final String $className = "NegPrim";
    public static final String $ruleString =
        "<prim>:NegPrim ::= NEGOP";


    public NegPrim() {
//NegPrim:init//

    }

    public static NegPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:NegPrim", scn$.lno);
        scn$.match(Token.Match.NEGOP, trace$);
        return new NegPrim();
    }


    public String toString() {
        return "neg";
    }

    public Val apply(Val [] va) {
        if (va.length != 1)
            throw new PLCCException("one argument expected");
        int i0 = va[0].intVal().val;
        return new IntVal(-i0);
    }


//NegPrim//
}
