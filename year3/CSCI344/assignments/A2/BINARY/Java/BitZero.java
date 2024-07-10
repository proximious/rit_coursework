//BitZero:top//
//BitZero:import//
import java.util.*;

// <bit>:BitZero ::= ZERO
public class BitZero extends Bit /*BitZero:class*/ {

    public static final String $className = "BitZero";
    public static final String $ruleString =
        "<bit>:BitZero ::= ZERO";


    public BitZero() {
//BitZero:init//

    }

    public static BitZero parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<bit>:BitZero", scn$.lno);
        scn$.match(Token.Match.ZERO, trace$);
        return new BitZero();
    }

	public int eval() {
		return 0;
	}

//BitZero//
}
