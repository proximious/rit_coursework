//SubPrim:top//
//SubPrim:import//
import java.util.*;

// <prim>:SubPrim ::= SUBOP
public class SubPrim extends Prim /*SubPrim:class*/ {

    public static final String $className = "SubPrim";
    public static final String $ruleString =
        "<prim>:SubPrim ::= SUBOP";


    public SubPrim() {
//SubPrim:init//

    }

    public static SubPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:SubPrim", scn$.lno);
        scn$.match(Token.Match.SUBOP, trace$);
        return new SubPrim();
    }


    public String toString() {
	return "-";
    }

    public Val apply(Val [] va) {
	if (va.length != 2)
	    throw new PLCCException("two arguments expected");
	int i0 = va[0].intVal().val;
	int i1 = va[1].intVal().val;
	return new IntVal(i0 - i1);
    }

//SubPrim//
}
