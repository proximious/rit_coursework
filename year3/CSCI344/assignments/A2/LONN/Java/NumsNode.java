//NumsNode:top//
//NumsNode:import//
import java.util.*;

// <nums>:NumsNode ::= <NUM> <nums>
public class NumsNode extends Nums /*NumsNode:class*/ {

    public static final String $className = "NumsNode";
    public static final String $ruleString =
        "<nums>:NumsNode ::= <NUM> <nums>";

    public Token num;
    public Nums nums;
    public NumsNode(Token num, Nums nums) {
//NumsNode:init//
        this.num = num;
        this.nums = nums;
    }

    public static NumsNode parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<nums>:NumsNode", scn$.lno);
        Token num = scn$.match(Token.Match.NUM, trace$);
        Nums nums = Nums.parse(scn$, trace$);
        return new NumsNode(num, nums);
    }

    public int min(int minSoFar) {
	String current = num.toString();
	int currentInt = Integer.parseInt(current);

	if (currentInt < minSoFar){
		minSoFar = currentInt;
	}

	return nums.min(minSoFar);

	//return 0; // This is just a stub. Remove this line ...
        // ... and complete the method definition.
        // This will be recursive!
    }

//NumsNode//
}
