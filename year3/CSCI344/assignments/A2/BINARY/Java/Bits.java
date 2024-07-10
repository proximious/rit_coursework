//Bits:top//
//Bits:import//
import java.util.*;

// <bits> **= <bit>
public class Bits /*Bits:class*/ {

    public static final String $className = "Bits";
    public static final String $ruleString =
        "<bits> **= <bit>";

    public List<Bit> bitList;
    public Bits(List<Bit> bitList) {
//Bits:init//
        this.bitList = bitList;
    }

    public static Bits parse(Scan scn$, Trace trace$) {
        if (trace$ != null)
            trace$ = trace$.nonterm("<bits>", scn$.lno);
        List<Bit> bitList = new ArrayList<Bit>();
        while (true) {
            Token t$ = scn$.cur();
            Token.Match match$ = t$.match;
            switch(match$) {
            case ZERO:
            case ONE:
                bitList.add(Bit.parse(scn$, trace$));
                continue;
            default:
                return new Bits(bitList);
            }
        }

    }

	public int eval(){
		int val = 0;

		if (bitList.size() == 0) {
			throw new PLCCException("no bits to interpret");
		}

		for (int i = 0; i < bitList.size(); i++){
			val = 2 * val + bitList.get(i).eval();
		}

		return val;
	}

//Bits//
}
