//DivPrim:top//
//DivPrim:import//
import java.util.*;

// <prim>:DivPrim ::= DIVOP
public class DivPrim extends Prim /*DivPrim:class*/ {

    public static final String $className = "DivPrim";
    public static final String $ruleString =
        "<prim>:DivPrim ::= DIVOP";


    public DivPrim() {
//DivPrim:init//

    }

    public static DivPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:DivPrim", scn$.lno);
        scn$.match(Token.Match.DIVOP, trace$);
        return new DivPrim();
    }


    public String toString() {
	return "/";
    }

    public Val apply(Val [] va) {
	if (va.length != 2)
	    throw new PLCCException("two arguments expected");
	IntVal i0 = va[0].intVal();
	IntVal i1 = va[1].intVal();
	if (i1.val.signum() == 0)
            throw new PLCCException("attempt to divide by zero");
	return i0.divide(i1);
    }


//DivPrim//
}
