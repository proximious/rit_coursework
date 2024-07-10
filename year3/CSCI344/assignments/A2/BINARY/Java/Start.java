//Start:top//
//Start:import//
import java.util.*;

// <start> ::= <bits> NL
public class Start extends _Start /*Start:class*/ {

    public static final String $className = "Start";
    public static final String $ruleString =
        "<start> ::= <bits> NL";

    public Bits bits;
    public Start(Bits bits) {
//Start:init//
        this.bits = bits;
    }

    public static Start parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<start>", scn$.lno);
        Bits bits = Bits.parse(scn$, trace$);
        scn$.match(Token.Match.NL, trace$);
        return new Start(bits);
    }

	public void $run() {
		System.out.println(bits.eval());
	}

//Start//
}
