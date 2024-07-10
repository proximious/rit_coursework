//BitOne:top//
//BitOne:import//
import java.util.*;

// <bit>:BitOne ::= ONE
public class BitOne extends Bit /*BitOne:class*/ {

    public static final String $className = "BitOne";
    public static final String $ruleString =
        "<bit>:BitOne ::= ONE";


    public BitOne() {
//BitOne:init//

    }

    public static BitOne parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<bit>:BitOne", scn$.lno);
        scn$.match(Token.Match.ONE, trace$);
        return new BitOne();
    }

	public int eval() {
		return 1;
	}

//BitOne//
}
