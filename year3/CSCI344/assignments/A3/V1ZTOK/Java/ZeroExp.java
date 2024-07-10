//ZeroExp:top//
//ZeroExp:import//
import java.util.*;

// <exp>:ZeroExp ::= ZERO
public class ZeroExp extends Exp /*ZeroExp:class*/ {

    public static final String $className = "ZeroExp";
    public static final String $ruleString =
        "<exp>:ZeroExp ::= ZERO";


    public ZeroExp() {
//ZeroExp:init//

    }

    public static ZeroExp parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<exp>:ZeroExp", scn$.lno);
        scn$.match(Token.Match.ZERO, trace$);
        return new ZeroExp();
    }


	public Val eval (Env env) {
		return new IntVal(0);
	}


//ZeroExp//
}
