//ZeropPrim:top//
//ZeropPrim:import//
import java.util.*;

// <prim>:ZeropPrim ::= ZEROP
public class ZeropPrim extends Prim /*ZeropPrim:class*/ {

    public static final String $className = "ZeropPrim";
    public static final String $ruleString =
        "<prim>:ZeropPrim ::= ZEROP";


    public ZeropPrim() {
//ZeropPrim:init//

    }

    public static ZeropPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:ZeropPrim", scn$.lno);
        scn$.match(Token.Match.ZEROP, trace$);
        return new ZeropPrim();
    }


    public String toString() {
	return "zero?";
    }

    public Val apply(Val [] va) {
        if (va.length != 1)
	    throw new PLCCException("one argument expected");
	int i0 = va[0].intVal().val;
	return new IntVal(i0 == 0 ? 1 : 0);
    }


//ZeropPrim//
}
