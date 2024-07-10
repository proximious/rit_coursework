//AddPrim:top//
//AddPrim:import//
import java.util.*;

// <prim>:AddPrim ::= ADDOP
public class AddPrim extends Prim /*AddPrim:class*/ {

    public static final String $className = "AddPrim";
    public static final String $ruleString =
        "<prim>:AddPrim ::= ADDOP";


    public AddPrim() {
//AddPrim:init//

    }

    public static AddPrim parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<prim>:AddPrim", scn$.lno);
        scn$.match(Token.Match.ADDOP, trace$);
        return new AddPrim();
    }


    public String toString() {
	return "+";
    }

    public Val apply(Val [] va) {
	if (va.length != 2)
	    throw new PLCCException("two arguments expected");
	int i0 = va[0].intVal().val;
	int i1 = va[1].intVal().val;
	return new IntVal(i0 + i1);
    }

//AddPrim//
}
