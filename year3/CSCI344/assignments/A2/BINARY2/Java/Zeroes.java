//Zeroes:top//
//Zeroes:import//
import java.util.*;

// <start>:Zeroes ::= ZERO NL
public class Zeroes extends Start /*Zeroes:class*/ {

    public static final String $className = "Zeroes";
    public static final String $ruleString =
        "<start>:Zeroes ::= ZERO NL";


    public Zeroes() {
//Zeroes:init//

    }

    public static Zeroes parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<start>:Zeroes", scn$.lno);
        scn$.match(Token.Match.ZERO, trace$);
        scn$.match(Token.Match.NL, trace$);
        return new Zeroes();
    }

	public void $run() {
		System.out.println("0");
	}

//Zeroes//
}
