//Ones:top//
//Ones:import//
import java.util.*;

// <start>:Ones ::= ONE <bits> NL
public class Ones extends Start /*Ones:class*/ {

    public static final String $className = "Ones";
    public static final String $ruleString =
        "<start>:Ones ::= ONE <bits> NL";

    public Bits bits;
    public Ones(Bits bits) {
//Ones:init//
        this.bits = bits;
    }

    public static Ones parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<start>:Ones", scn$.lno);
        scn$.match(Token.Match.ONE, trace$);
        Bits bits = Bits.parse(scn$, trace$);
        scn$.match(Token.Match.NL, trace$);
        return new Ones(bits);
    }

	public void $run() {
		System.out.println(bits.eval());
	}

//Ones//
}
